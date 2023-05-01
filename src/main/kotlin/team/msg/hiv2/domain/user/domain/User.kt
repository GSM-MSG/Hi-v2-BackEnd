package team.msg.hiv2.domain.user.domain

import team.msg.hiv2.domain.user.domain.constant.UserRole
import java.util.UUID

data class User(
    val id: UUID,
    val email: String,
    val name: String,
    val grade: Int,
    val classNum: Int,
    val number: Int,
    val profileImageUrl: String,
    val roles: MutableList<UserRole>
)
