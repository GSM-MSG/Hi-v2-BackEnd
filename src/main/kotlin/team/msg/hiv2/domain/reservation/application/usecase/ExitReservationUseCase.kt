package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.exception.ForbiddenExitReservationException
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class ExitReservationUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService
) {

    fun execute(reservationId: UUID){
        val reservation = reservationService.queryReservationById(reservationId)

        if(reservation.userIds.size < 3)
            throw ForbiddenExitReservationException()

        val currentUser = userService.queryCurrentUser()

        val updatedUserIds = reservation.userIds.filter { it != currentUser.id }
        reservationService.save(reservation.copy(userIds = updatedUserIds.toMutableList()))
    }
}