package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.spi.ReservationPort
import team.msg.hiv2.domain.reservation.exception.ReservationNotFoundException
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class DeleteReservationUseCase(
    private val reservationPort: ReservationPort,
    private val userPort: UserPort
) {

    fun execute(reservationId: UUID){
        val reservation = reservationPort.queryReservationById(reservationId)
            ?: throw ReservationNotFoundException()
        val users = userPort.queryAllUserByReservation(reservation)

        userPort.saveAll(users.map { it.copy(useStatus = UseStatus.AVAILABLE) })
        reservationPort.deleteReservation(reservation)
    }
}