package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.service.QueryHomeBaseService
import team.msg.hiv2.domain.homebase.presentation.data.response.HomeBaseResponse
import team.msg.hiv2.domain.reservation.application.spi.QueryReservationPort
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryReservationByHomeBaseUseCase(
    private val homeBaseService: QueryHomeBaseService,
    private val userService: UserService,
    private val reservationPort: QueryReservationPort
) {

    fun execute(floor: Int, period: Int): List<ReservationResponse> {

        val homeBases = homeBaseService.queryHomeBaseByFloorAndPeriod(floor, period)

        return homeBases.map {
            reservationPort.queryReservationByHomeBaseId(it.id)?.let { reservation ->
                val users = userService.queryAllUserById(reservation.userIds)

                ReservationResponse.of(
                    reservation,
                    users.map(UserResponse::of),
                    HomeBaseResponse.of(it)
                )
            } ?: ReservationResponse.of(
                null,
                null,
                HomeBaseResponse.of(it)
            )
        }
    }
}