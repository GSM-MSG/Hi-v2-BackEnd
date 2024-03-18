package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.service.QueryHomeBaseService
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.response.HomeBaseResponse
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryReservationByHomeBaseUseCase(
    private val reservationService: ReservationService,
    private val homeBaseService: QueryHomeBaseService,
    private val userService: UserService,
    private val teamService: TeamService
) {

    fun execute(floor: Int, period: Int): List<ReservationResponse> {

        val homeBase = homeBaseService.queryHomeBaseByFloorAndPeriodAndHomeBaseNumber(floor, period)

        val reservations = reservationService.queryAllReservationByHomeBase(homeBase)

        return reservations.map {
            val team = teamService.queryTeamById(it.teamId)
            val users = userService.queryAllUserById(team.userIds)
            ReservationResponse.of(
                it,
                users.map { user -> UserResponse.of(user) },
                HomeBaseResponse(homeBase.id, floor, period)
            )
        }
    }
}