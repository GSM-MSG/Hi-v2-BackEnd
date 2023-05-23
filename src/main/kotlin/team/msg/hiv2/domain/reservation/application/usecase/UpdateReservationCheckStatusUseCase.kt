package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.spi.ReservationPort
import team.msg.hiv2.domain.reservation.exception.ReservationNotFoundException
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationCheckStatusRequest
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class UpdateReservationCheckStatusUseCase(
    private val reservationPort: ReservationPort,
    private val userPort: UserPort
) {

    fun execute(reservationId: UUID, request: UpdateReservationCheckStatusRequest){
        val reservation = reservationPort.queryReservationById(reservationId)
            ?: throw ReservationNotFoundException()
        reservationPort.save(reservation.copy(checkStatus = request.checkStatus))
    }
}