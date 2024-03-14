package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class UpdateReservationUseCase(
    private val reservationService: ReservationService,
    private val userService: UserService,
    private val userValidator: UserValidator,
    private val teamService: TeamService
) {

    fun execute(reservationId: UUID, request: UpdateReservationRequest){

        val reservation = reservationService.queryReservationById(reservationId)

        val team = teamService.queryTeamById(reservation.teamId)

        val users = request.users.map { userService.queryUserById(it) }

        userValidator.checkUsersUseStatus(users)

        teamService.save(team.copy(userIds = users.map { it.id }.toMutableList()))
        reservationService.save(reservation.copy(reason = request.reason))
        userService.saveAll(users.map { it.copy(useStatus = UseStatus.UNAVAILABLE) })
    }
}