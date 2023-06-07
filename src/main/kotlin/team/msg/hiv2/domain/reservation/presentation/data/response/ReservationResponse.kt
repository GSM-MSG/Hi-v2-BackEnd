package team.msg.hiv2.domain.reservation.presentation.data.response

import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import java.util.*

data class ReservationResponse(
    val reservationId: UUID?,
    val users: List<UserResponse>?
)
