package team.msg.hiv2.domain.reservation.presentation.data.response

import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.homebase.presentation.data.response.HomeBaseResponse
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse

data class ReservationDetailResponse(
    val users: List<UserResponse>,
    val reason: String,
    val checkStatus: Boolean,
    val homeBase: HomeBaseResponse
) {
    companion object {
        fun of(reservation: Reservation, users: List<UserResponse>, homeBase: HomeBase) = ReservationDetailResponse(
            users = users,
            reason = reservation.reason,
            checkStatus = reservation.checkStatus,
            homeBase = HomeBaseResponse.of(homeBase)
        )
    }
}
