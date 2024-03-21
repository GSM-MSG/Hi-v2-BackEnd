package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.exception.AlreadyExistReservationException
import team.msg.hiv2.domain.homebase.exception.TooManyUsersException
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class UpdateReservationUseCase(
    private val reservationService: ReservationService,
    private val userService: UserService,
    private val teamService: TeamService,
    private val homeBaseService: HomeBaseService
) {

    fun execute(reservationId: UUID, request: UpdateReservationRequest) {

        val reservation = reservationService.queryReservationById(reservationId)

        val homeBase = homeBaseService.queryHomeBaseById(reservation.homeBaseId)

        if (request.users.size > homeBase.maxCapacity)
            throw TooManyUsersException()

        val homeBases = homeBaseService.queryHomeBaseByPeriod(homeBase.period)
        val teamIds = reservationService.queryAllReservationByHomeBaseIn(homeBases).map { reservation -> reservation.teamId }
        val userIds = teamService.queryAllTeamByIdIn(teamIds).flatMap { team -> team.userIds }

        val team = teamService.queryTeamById(reservation.teamId)

        if ((userIds.subtract(team.userIds.toSet()).containsAll(request.users)))
            throw AlreadyExistReservationException()

        val users = userService.queryAllUserById(request.users)

        if (request.users.size != users.size)
            throw UserNotFoundException()

        teamService.save(team.copy(userIds = users.map { it.id }.toMutableList()))
        reservationService.save(reservation.copy(reason = request.reason))
    }
}