package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.CommandReservationService
import team.msg.hiv2.domain.reservation.application.service.QueryReservationService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class DelegateRepresentativeUseCase(
    private val userService: UserService,
    private val queryReservationService: QueryReservationService,
    private val commandReservationService: CommandReservationService,
    private val userValidator: UserValidator
) {

    fun execute(reservationId: UUID, userId: UUID){
        val currentUser = userService.queryCurrentUser()
        val reservation = queryReservationService.queryReservationById(reservationId)

        userValidator.checkRepresentative(currentUser, reservation)

        val delegatedUser = userService.queryUserByIdAndReservation(userId, reservation)

        commandReservationService.save(reservation.copy(representativeId = delegatedUser.id))
    }
}