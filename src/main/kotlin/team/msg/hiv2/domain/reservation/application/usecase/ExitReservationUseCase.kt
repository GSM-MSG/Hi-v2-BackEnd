package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.spi.ReservationPort
import team.msg.hiv2.domain.reservation.exception.ReservationNotFoundException
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class ExitReservationUseCase(
    private val userPort: UserPort,
    private val reservationPort: ReservationPort,
    private val userValidator: UserValidator
) {

    fun execute(reservationId: UUID){
        val reservation = reservationPort.queryReservationById(reservationId)
            ?: throw ReservationNotFoundException()
        val currentUser = userPort.queryCurrentUser()

        userValidator.checkUserAndReservation(currentUser, reservation)

        userPort.save(currentUser.copy(reservationId = null))
    }
}