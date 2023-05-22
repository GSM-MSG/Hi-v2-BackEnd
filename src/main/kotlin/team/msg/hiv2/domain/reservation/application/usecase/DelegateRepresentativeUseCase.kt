package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.spi.ReservationPort
import team.msg.hiv2.domain.reservation.exception.ReservationNotFoundException
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class DelegateRepresentativeUseCase(
    private val userPort: UserPort,
    private val reservationPort: ReservationPort,
    private val userValidator: UserValidator
) {

    fun execute(reservationId: UUID, userId: UUID){
        val currentUser = userPort.queryCurrentUser()
        val reservation = reservationPort.queryReservationById(reservationId)
            ?: throw ReservationNotFoundException()

        userValidator.checkRepresentative(currentUser, reservation)

        val delegatedUser = userPort.queryUserByIdAndReservation(userId, reservation)
            ?: throw UserNotFoundException()

        reservationPort.saveReservation(reservation.copy(representativeId = delegatedUser.id))
    }
}