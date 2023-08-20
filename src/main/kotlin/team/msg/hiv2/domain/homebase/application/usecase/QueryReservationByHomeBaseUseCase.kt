package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.service.QueryHomeBaseService
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryReservationByHomeBaseUseCase(
    private val reservationService: ReservationService,
    private val homeBaseService: QueryHomeBaseService,
    private val userService: UserService
) {

    fun execute(floor: Int, period: Int): List<ReservationResponse>{
        val homeBase = homeBaseService.queryHomeBaseByFloorAndPeriod(floor, period)

        val reservations = reservationService.queryAllReservationByHomeBase(homeBase)

        return reservations.map {
            val users = userService.queryAllUserByReservation(it)
            ReservationResponse.of(it, users.map { user
                -> UserResponse.of(user) })
        }
    }
}