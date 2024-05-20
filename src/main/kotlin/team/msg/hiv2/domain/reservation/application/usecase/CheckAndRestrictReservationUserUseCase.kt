package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class CheckAndRestrictReservationUserUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService
) {

    fun execute() {
        val reservations = reservationService.queryAllReservation()

        reservations.map { reservation ->
            if (!reservation.checkStatus) {
                val users = userService.queryAllUserById(reservation.userIds)

                userService.saveAll(users.map { it.copy(useStatus = UseStatus.UNAVAILABLE) })
            }
        }

        reservationService.deleteAllInBatch()
    }
}