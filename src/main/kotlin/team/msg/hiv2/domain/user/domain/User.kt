package team.msg.hiv2.domain.user.domain

import java.util.UUID

data class User(
    val id: UUID,
    val email: String,
    val name: String,
    val grade: Int,
    val classNum: Int,
    val number: Int,
    val profileUrl: String,
)
