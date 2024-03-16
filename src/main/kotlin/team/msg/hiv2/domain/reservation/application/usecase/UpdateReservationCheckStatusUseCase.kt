package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationCheckStatusRequest
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class UpdateReservationCheckStatusUseCase(
    private val reservationService: ReservationService
) {

    fun execute(reservationId: UUID, request: UpdateReservationCheckStatusRequest) {
        val reservation = reservationService.queryReservationById(reservationId)

        reservationService.save(reservation.copy(checkStatus = request.checkStatus))
    }
}