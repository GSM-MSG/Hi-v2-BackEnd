package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.exception.AlreadyExistReservationException
import team.msg.hiv2.domain.homebase.exception.ForbiddenReserveException
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.team.domain.Team
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class ReserveHomeBaseUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService,
    private val homeBaseService: HomeBaseService,
    private val teamService: TeamService
) {

    fun execute(floor: Int, period: Int, homeBaseNumber: Int, request: ReservationHomeBaseRequest) {

        val homeBase = homeBaseService.queryHomeBaseByFloorAndPeriodAndHomeBaseNumber(floor, period, homeBaseNumber)

        val users = userService.queryAllUserById(request.users)

        if (request.users.size != users.size)
            throw UserNotFoundException()

        val reservationCount = reservationService.countReservationByHomeBase(homeBase)
        when(floor) {
            2 -> if(reservationCount > 3) throw ForbiddenReserveException()
            3 -> if(reservationCount > 5) throw ForbiddenReserveException()
            4 -> if(reservationCount > 5) throw ForbiddenReserveException()
        }

        if(reservationService.existsByHomeBase(homeBase))
            throw AlreadyExistReservationException()

        val team = teamService.save(
            Team(
                id = UUID.randomUUID(),
                userIds = users.map { it.id }.toMutableList()
            )
        )

        reservationService.save(
            Reservation(
                id = UUID.randomUUID(),
                reason = request.reason,
                homeBaseId = homeBase.id,
                teamId = team.id,
                checkStatus = false
            )
        )
    }
}