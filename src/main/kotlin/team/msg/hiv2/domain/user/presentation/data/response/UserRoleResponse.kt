package team.msg.hiv2.domain.user.presentation.data.response

import team.msg.hiv2.domain.user.domain.constant.UserRole
import java.util.UUID

data class UserRoleResponse(
    val userId: UUID,
    val role: MutableList<UserRole>
) {
    companion object {
        fun of(userId: UUID, role: MutableList<UserRole>) = UserRoleResponse(
            userId = userId,
            role = role
        )
    }
}