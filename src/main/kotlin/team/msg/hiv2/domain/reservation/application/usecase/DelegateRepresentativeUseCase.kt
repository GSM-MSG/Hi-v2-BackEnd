package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class DelegateRepresentativeUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService
) {

    fun execute(reservationId: UUID, userId: UUID){

        val reservation = reservationService.queryReservationById(reservationId)

        val delegatedUser = userService.queryUserByIdAndReservation(userId, reservation)

        reservationService.save(reservation.copy(representativeId = delegatedUser.id))
    }
}