package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.spi.QueryHomeBasePort
import team.msg.hiv2.domain.homebase.exception.HomeBaseNotFoundException
import team.msg.hiv2.domain.reservation.application.spi.QueryReservationPort
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryReservationUseCase(
    private val queryReservationPort: QueryReservationPort,
    private val queryHomeBasePort: QueryHomeBasePort,
    private val queryUserPort: QueryUserPort
) {

    fun execute(floor: Int, period: Int): List<ReservationResponse>{
        val homeBase = queryHomeBasePort.queryHomeBaseByFloorAndPeriod(floor, period)
            .let { it ?: throw HomeBaseNotFoundException() }
        val reservations = queryReservationPort.queryAllReservationByHomeBase(homeBase)

        return reservations.map {
            val users = queryUserPort.queryAllUserByReservation(it)
            ReservationResponse(
                reservationId = it.id,
                users = users.map { user -> UserResponse(
                    userId = user.id,
                    name = user.name
                    )
                }
            )
        }
    }
}