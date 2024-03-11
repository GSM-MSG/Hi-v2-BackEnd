package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.exception.AlreadyExistReservationException
import team.msg.hiv2.domain.homebase.exception.ForbiddenReserveException
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class ReserveHomeBaseUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService,
    private val homeBaseService: HomeBaseService
) {

    fun execute(floor: Int, period: Int, request: ReservationHomeBaseRequest) {

        val currentUser = userService.queryCurrentUser()

        val homeBase = homeBaseService.queryHomeBaseByFloorAndPeriod(floor, period)

        val users = userService.queryAllUserById(request.users)

        val reservationCount = reservationService.countReservationByHomeBase(homeBase)
        when(floor) {
            2 -> if(reservationCount > 3) throw ForbiddenReserveException()
            3 -> if(reservationCount > 5) throw ForbiddenReserveException()
            4 -> if(reservationCount > 5) throw ForbiddenReserveException()
        }

        if(reservationService.existsByHomeBaseAndReservationNumber(homeBase, request.reservationNumber))
            throw AlreadyExistReservationException()

        val reservationId = reservationService.save(
            Reservation(
                id = UUID.randomUUID(),
                reason = request.reason,
                representativeId = currentUser.id,
                homeBaseId = homeBase.id,
                checkStatus = false,
                reservationNumber = request.reservationNumber
            )
        ).id

        userService.saveAll(users.map {
            it.copy(reservationId = reservationId, useStatus = UseStatus.UNAVAILABLE)
        })
    }
}