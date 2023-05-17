package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationDetailResponse
import team.msg.hiv2.domain.reservation.application.spi.QueryReservationPort
import team.msg.hiv2.domain.reservation.exception.ReservationNotFoundException
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase
import java.util.UUID

@ReadOnlyUseCase
class QueryReservationDetailUseCase(
    private val queryReservationPort: QueryReservationPort,
    private val queryUserPort: QueryUserPort
) {

    fun execute(id: UUID): ReservationDetailResponse {
        val reservation = queryReservationPort.queryReservationById(id)
            ?: throw ReservationNotFoundException()
        val users = queryUserPort.queryAllUserByReservation(reservation)

        return ReservationDetailResponse(
            reservationId = reservation.id,
            users = users.map {
                UserResponse(
                    userId = it.id,
                    name = it.name
                )
            },
            reason = reservation.reason,
            checkStatus = reservation.checkStatus
        )
    }
}