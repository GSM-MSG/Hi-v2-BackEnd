package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class CheckAndRestrictReservationUserUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService,
    private val teamService: TeamService
) {

    fun execute(id: UUID){
        val reservation = reservationService.queryReservationById(id)

        val team = teamService.queryTeamById(reservation.teamId)

        val users = userService.queryAllUsersByUserIds(team.userIds)

        if(!reservation.checkStatus) {
            userService.saveAll(users.map { it.copy(useStatus = UseStatus.UNAVAILABLE) })
        }
    }
}