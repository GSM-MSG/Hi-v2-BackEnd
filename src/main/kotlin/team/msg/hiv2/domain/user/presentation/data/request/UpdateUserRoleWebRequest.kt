package team.msg.hiv2.domain.user.presentation.data.request

import jakarta.validation.constraints.NotNull
import team.msg.hiv2.domain.user.domain.constant.UserRole

data class UpdateUserRoleWebRequest(
    @NotNull
    val role: UserRole
)