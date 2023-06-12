package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.CommandReservationService
import team.msg.hiv2.domain.reservation.application.service.QueryReservationService
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationCheckStatusRequest
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class UpdateReservationCheckStatusUseCase(
    private val commandReservationService: CommandReservationService,
    private val queryReservationService: QueryReservationService
) {

    fun execute(reservationId: UUID, request: UpdateReservationCheckStatusRequest){
        val reservation = queryReservationService.queryReservationById(reservationId)
        commandReservationService.save(reservation.copy(checkStatus = request.checkStatus))
    }
}