package team.msg.hiv2.domain.reservation.presentation.data.response

import team.msg.hiv2.domain.homebase.presentation.data.response.HomeBaseResponse
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import java.util.*

data class ReservationResponse(
    val reservationId: UUID?,
    val users: List<UserResponse>?,
    val checkStatus: Boolean?,
    val reason: String?,
    val homeBase: HomeBaseResponse?
) {
    companion object {
        fun of(reservation: Reservation?, users: List<UserResponse>?, homeBase: HomeBaseResponse?) = ReservationResponse(
            reservationId = reservation?.id,
            users = users,
            checkStatus = reservation?.checkStatus,
            reason = reservation?.reason,
            homeBase = homeBase
        )
    }
}