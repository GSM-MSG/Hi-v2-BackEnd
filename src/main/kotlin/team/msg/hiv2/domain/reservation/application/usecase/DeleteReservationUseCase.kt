package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class DeleteReservationUseCase(
    private val reservationService: ReservationService,
    private val userService: UserService
) {

    fun execute(reservationId: UUID){
        val reservation = reservationService.queryReservationById(reservationId)

        val users = userService.queryAllUserByReservation(reservation)

        val updatedUsers = users.map { it.copy(useStatus = UseStatus.UNAVAILABLE, reservationId = null) }

        userService.saveAll(updatedUsers)
        reservationService.delete(reservation)
    }
}