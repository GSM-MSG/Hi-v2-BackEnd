package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.exception.ForbiddenExitReservationException
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class ExitReservationUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService,
    private val teamService: TeamService
) {

    fun execute(reservationId: UUID){

        val reservation = reservationService.queryReservationById(reservationId)

        val currentUser = userService.queryCurrentUser()

        // count 로 바꾸기
        val team = teamService.queryTeamById(reservation.teamId)

        if(team.userIds.size < 3)
            throw ForbiddenExitReservationException()

        reservationService.delete(reservation)
        teamService.deleteTeamById(reservation.teamId)
        userService.save(currentUser.copy(useStatus = UseStatus.AVAILABLE))
    }
}