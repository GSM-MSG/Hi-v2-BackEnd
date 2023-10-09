package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
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

        val reservation = user.reservationId?.let { reservationService.queryReservationById(it) }

        val homeBase = reservation?.let { homeBaseService.queryHomeBaseById(it.homeBaseId) }

        return UserInfoResponse.of(
            user,
            reservation?.let {
                val users = userService.queryAllUserByReservation(it)
                ReservationResponse.of(it, users.map { user -> UserResponse.of(user) }, homeBase!!) }
        )
    }
}