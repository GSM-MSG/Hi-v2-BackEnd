package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.CommandReservationService
import team.msg.hiv2.domain.reservation.application.service.QueryReservationService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class DeleteReservationUseCase(
    private val queryReservationService: QueryReservationService,
    private val commandReservationService: CommandReservationService,
    private val userService: UserService,
    private val userValidator: UserValidator
) {

    fun execute(reservationId: UUID){
        val reservation = queryReservationService.queryReservationById(reservationId)
        val users = userService.queryAllUserByReservation(reservation)
        val currentUser = userService.queryCurrentUser()

        userValidator.checkRepresentative(currentUser, reservation)

        userService.saveAll(users.map { it.copy(useStatus = UseStatus.AVAILABLE) })
        commandReservationService.delete(reservation)
    }
}