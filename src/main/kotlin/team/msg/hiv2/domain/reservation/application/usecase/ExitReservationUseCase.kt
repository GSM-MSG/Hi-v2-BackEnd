package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.exception.ForbiddenExitReservationException
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class ExitReservationUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService,
    private val teamService: TeamService
) {

    fun execute(reservationId: UUID){
        val reservation = reservationService.queryReservationById(reservationId)

        // count 로 바꾸기
        val team = teamService.queryTeamById(reservation.teamId)

        if(team.userIds.size < 3)
            throw ForbiddenExitReservationException()

        val currentUser = userService.queryCurrentUser()

        val updatedUserIds = team.userIds.filter { it != currentUser.id }
        teamService.save(team.copy(userIds = updatedUserIds.toMutableList()))
    }
}