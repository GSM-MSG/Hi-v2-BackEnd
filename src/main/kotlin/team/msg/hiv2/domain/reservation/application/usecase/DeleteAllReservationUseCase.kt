package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.CommandReservationService
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class DeleteAllReservationUseCase(
    private val commandReservationService: CommandReservationService
) {

    fun execute() {
        commandReservationService.deleteAllInBatch()
    }
}