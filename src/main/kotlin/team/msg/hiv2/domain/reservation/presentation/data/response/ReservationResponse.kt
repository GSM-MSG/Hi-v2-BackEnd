package team.msg.hiv2.domain.reservation.presentation.data.response

import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import java.util.*

data class ReservationResponse(
    val reservationId: UUID,
    val users: List<UserResponse>,
    val reservationNumber: Int,
    val checkStatus: Boolean,
    val homeBaseResponses: List<HomeBaseResponse>
) {
    companion object {
        fun of(reservation: Reservation, users: List<UserResponse>, homeBases: List<HomeBaseResponse>) = ReservationResponse(
            reservationId = reservation.id,
            users = users,
            reservationNumber = reservation.reservationNumber,
            checkStatus = reservation.checkStatus,
            homeBaseResponses = homeBases
        )
    }
}

data class HomeBaseResponse(
    val homeBaseId: Long,
    val floor: Int,
    val period: Int
)
