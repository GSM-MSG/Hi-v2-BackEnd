package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.CommandReservationService
import team.msg.hiv2.domain.reservation.application.service.QueryReservationService
import team.msg.hiv2.domain.user.application.service.CommandUserService
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class DeleteReservationUseCase(
    private val queryReservationService: QueryReservationService,
    private val commandReservationService: CommandReservationService,
    private val queryUserService: QueryUserService,
    private val commandUserService: CommandUserService,
    private val userValidator: UserValidator
) {

    fun execute(reservationId: UUID){
        val reservation = queryReservationService.queryReservationById(reservationId)
        val users = queryUserService.queryAllUserByReservation(reservation)
        val currentUser = queryUserService.queryCurrentUser()

        userValidator.checkRepresentative(currentUser, reservation)

        commandUserService.saveAll(users.map { it.copy(useStatus = UseStatus.AVAILABLE) })
        commandReservationService.delete(reservation)
    }
}