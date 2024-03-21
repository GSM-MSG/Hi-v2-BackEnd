package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.exception.AlreadyExistReservationException
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.user.application.service.UserService
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

        val homeBases = homeBaseService.queryHomeBaseById(reservation.homeBaseId).let { homeBaseService.queryHomeBaseByPeriod(it.period) }
        val teamIds = reservationService.queryAllReservationByHomeBaseIn(homeBases).map { reservation -> reservation.teamId }
        val userIds = teamService.queryAllTeamByIdIn(teamIds).flatMap { team -> team.userIds }

        if (userIds.containsAll(request.users))
            throw AlreadyExistReservationException()

        val team = teamService.queryTeamById(reservation.teamId)

        val users = userService.queryAllUserById(request.users)

        teamService.save(team.copy(userIds = users.map { it.id }.toMutableList()))
        reservationService.save(reservation.copy(reason = request.reason))
    }
}