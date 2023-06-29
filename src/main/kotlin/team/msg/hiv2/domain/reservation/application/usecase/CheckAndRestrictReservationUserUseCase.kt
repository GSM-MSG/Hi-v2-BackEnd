package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class CheckAndRestrictReservationUserUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService
) {

    fun execute(id: UUID){
        val reservation = reservationService.queryReservationById(id)

        val users = userService.queryAllUserByReservation(reservation)

        if(!reservation.checkStatus) {
            userService.saveAll(users.map { it.copy(useStatus = UseStatus.UNAVAILABLE) })
        }
    }
}