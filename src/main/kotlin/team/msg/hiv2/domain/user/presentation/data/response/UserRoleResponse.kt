package team.msg.hiv2.domain.user.presentation.data.response

import team.msg.hiv2.domain.user.domain.constant.UserRole

data class UserRoleResponse(
    val role: UserRole
) {
    companion object {
        fun of(role: UserRole) = UserRoleResponse(
            role = role
        )
    }
}