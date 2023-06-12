package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.CommandReservationService
import team.msg.hiv2.domain.reservation.application.service.QueryReservationService
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.user.application.service.CommandUserService
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class UpdateReservationUseCase(
    private val commandReservationService: CommandReservationService,
    private val queryReservationService: QueryReservationService,
    private val queryUserService: QueryUserService,
    private val commandUserService: CommandUserService,
    private val userValidator: UserValidator
) {

    fun execute(reservationId: UUID, request: UpdateReservationRequest){
        val reservation = queryReservationService.queryReservationById(reservationId)
        val currentUser = queryUserService.queryCurrentUser()
        userValidator.checkRepresentative(currentUser, reservation)

        val prevUsers = queryUserService.queryAllUserByReservation(reservation)
        commandUserService.saveAll(prevUsers.map { it.copy(reservationId = null , useStatus = UseStatus.AVAILABLE) })

        val users = queryUserService.queryAllUserById(request.users)
        userValidator.checkUsersUseStatus(users)

        commandReservationService.save(reservation.copy(reason = request.reason))
        commandUserService.saveAll(users.map { it.copy(reservationId = reservationId) })
    }
}