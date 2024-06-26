package team.msg.hiv2.domain.user.presentation.data.response

import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import java.util.*

class UserInfoResponse(
    val userId: UUID,
    val email: String,
    val name: String,
    val schoolNumber: String?,
    val profileImageUrl: String,
    val role: UserRole,
    val useStatus: UseStatus,
    val reservations: List<ReservationResponse>
) {
    companion object {
        fun of(user: User, reservations: List<ReservationResponse>) = UserInfoResponse(
            userId = user.id,
            email = user.email,
            name = user.name,
            schoolNumber = user.schoolNumber,
            profileImageUrl = user.profileImageUrl,
            role = user.role,
            useStatus = user.useStatus,
            reservations = reservations
        )
    }
}