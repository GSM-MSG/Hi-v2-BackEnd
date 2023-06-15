package team.msg.hiv2.domain.user.presentation.data.response

import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import java.util.UUID

class UserInfoResponse(
    val userId: UUID,
    val name: String,
    val grade: Int?,
    val classNum: Int?,
    val number: Int?,
    val profileImageUrl: String,
    val reservation: ReservationResponse?,
    val useStatus: UseStatus
)