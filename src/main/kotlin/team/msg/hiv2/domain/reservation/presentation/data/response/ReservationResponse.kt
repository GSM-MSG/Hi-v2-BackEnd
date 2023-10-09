package team.msg.hiv2.domain.reservation.presentation.data.response

import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import java.util.*

data class ReservationResponse(
    val reservationId: UUID,
    val users: List<UserResponse>,
    val reservationNumber: Int,
    val homeBaseId: Long,
    val floor: Int,
    val period: Int
) {
    companion object {
        fun of(reservation: Reservation, users: List<UserResponse>, homeBase: HomeBase) = ReservationResponse(
            reservationId = reservation.id,
            users = users,
            reservationNumber = reservation.reservationNumber,
            homeBaseId = homeBase.id,
            floor = homeBase.floor,
            period = homeBase.period
        )
    }
}
