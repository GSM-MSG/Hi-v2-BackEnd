package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.presentation.data.response.HomeBaseResponse
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.response.UserInfoResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryUserInfoUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService,
    private val homeBaseService: HomeBaseService
) {

    fun execute(): UserInfoResponse {
        val user = userService.queryCurrentUser()

        val reservations = reservationService.queryAllReservationByUserIdInOrderByReservationId(user.id)

        return UserInfoResponse.of(
            user,
            reservations.map { reservation ->
                val users = userService.queryAllUserById(reservation.userIds)
                val homeBase = homeBaseService.queryHomeBaseById(reservation.homeBaseId)

                ReservationResponse.of(
                    reservation,
                    users.map { UserResponse.of(it) },
                    HomeBaseResponse.of(homeBase)
                )
            }
        )
    }
}