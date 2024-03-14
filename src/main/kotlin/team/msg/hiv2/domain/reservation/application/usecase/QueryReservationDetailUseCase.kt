package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationDetailResponse
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase
import java.util.UUID

@ReadOnlyUseCase
class QueryReservationDetailUseCase(
    private val reservationService: ReservationService,
    private val userService: UserService,
    private val teamService: TeamService
) {

    fun execute(id: UUID): ReservationDetailResponse {

        val reservation = reservationService.queryReservationById(id)

        val team = teamService.queryTeamById(reservation.teamId)

        val users = userService.queryAllUserById(team.userIds)

        return ReservationDetailResponse.of(reservation, users.map { UserResponse.of(it) })
    }
}