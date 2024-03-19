package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.service.QueryHomeBaseService
import team.msg.hiv2.domain.homebase.presentation.data.response.HomeBaseResponse
import team.msg.hiv2.domain.reservation.application.spi.QueryReservationPort
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.team.application.spi.TeamPort
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryReservationByHomeBaseUseCase(
    private val homeBaseService: QueryHomeBaseService,
    private val userService: UserService,
    private val reservationPort: QueryReservationPort,
    private val teamPort: TeamPort
) {

    fun execute(floor: Int, period: Int): List<ReservationResponse?> {

        val homeBases = homeBaseService.queryHomeBaseByFloorAndPeriod(floor, period)

        return homeBases.map {
            reservationPort.queryReservationByHomeBase(it)?.let { reservation ->
                teamPort.queryTeamById(reservation.teamId)?.let { team ->
                    val users = userService.queryAllUserById(team.userIds)

                    ReservationResponse.of(
                        reservation,
                        users.map(UserResponse::of),
                        HomeBaseResponse.of(it)
                    )
                }
            } ?: ReservationResponse.of(
                null,
                null,
                HomeBaseResponse.of(it)
            )
        }
    }
}