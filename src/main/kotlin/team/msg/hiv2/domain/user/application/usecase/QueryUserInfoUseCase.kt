package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.response.UserInfoResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryUserInfoUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService,
    private val homeBaseService: HomeBaseService,
    private val teamService: TeamService
) {

    fun execute(): UserInfoResponse {
        val user = userService.queryCurrentUser()

        val team = teamService.queryTeamByUserId(user.id)

        val reservations = reservationService.queryAllReservationByTeam(team)

        val homeBase = reservations.map { homeBaseService.queryHomeBaseById(it.homeBaseId) }

//        return UserInfoResponse.of(
//            user,
//            reservations.map {
////                val users = userService.queryAllUserByReservation(it)
//                val team = teamService.queryTeamById(it.teamId)
//                val users = userService.queryAllUsersByUserIds(team.userIds)
//                ReservationResponse.of(it, users.map { user -> UserResponse.of(user) }, homeBase!!)
//            }
//        )

        val reservationResponse = ReservationResponse.of()

        return UserInfoResponse.of(
            user,
            reservations.map {
                val users = userService.queryAllUsersByUserIds(team.userIds)
                ReservationResponse.of(
                    it, users, homeBase
                )
            }
        )
    }
}