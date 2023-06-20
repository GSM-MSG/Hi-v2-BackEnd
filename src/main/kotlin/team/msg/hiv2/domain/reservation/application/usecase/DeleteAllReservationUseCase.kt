package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class DeleteAllReservationUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService
) {

    fun execute(){

    }
}