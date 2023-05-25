package team.msg.hiv2.domain.user.presentation.data.response

import java.util.UUID

data class UserResponse(
    val userId: UUID,
    val name: String,
    val grade: Int,
    val classNum: Int,
    val number: Int
)
