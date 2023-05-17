package team.msg.hiv2.domain.homebase.presentation.data.response

import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import java.util.*

data class ReservationDetailResponse(
    val reservationId: UUID,
    val users: List<UserResponse>,
    val reason: String,
    val checkStatus: Boolean
)
