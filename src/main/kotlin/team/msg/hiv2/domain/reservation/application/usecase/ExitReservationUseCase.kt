package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.QueryReservationService
import team.msg.hiv2.domain.user.application.service.CommandUserService
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class ExitReservationUseCase(
    private val userService: UserService,
    private val queryReservationService: QueryReservationService,
    private val userValidator: UserValidator
) {

    fun execute(reservationId: UUID){
        val reservation = queryReservationService.queryReservationById(reservationId)
        val currentUser = userService.queryCurrentUser()

        userValidator.checkUserAndReservation(currentUser, reservation)

        userService.save(currentUser.copy(reservationId = null))
    }
}