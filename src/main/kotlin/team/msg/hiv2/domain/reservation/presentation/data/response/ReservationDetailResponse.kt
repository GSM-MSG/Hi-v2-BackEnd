package team.msg.hiv2.domain.reservation.presentation.data.response

import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import java.util.*

data class ReservationDetailResponse(
    val reservationId: UUID,
    val users: List<UserResponse>,
    val reason: String,
    val checkStatus: Boolean,
    val reservationNumber: Int
) {
    companion object {
        fun of(reservation: Reservation, users: List<UserResponse>) = ReservationDetailResponse(
            reservationId = reservation.id,
            users = users,
            reason = reservation.reason,
            checkStatus = reservation.checkStatus,
            reservationNumber = reservation.reservationNumber
        )
    }
}
