package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class DeleteReservationUseCase(
    private val reservationService: ReservationService
) {

    fun execute(reservationId: UUID) {
        val reservation = reservationService.queryReservationById(reservationId)

        reservationService.delete(reservation)
    }
}