package team.msg.hiv2.domain.notice.domain

import java.util.UUID

data class Notice(
    val id: UUID,
    val title: String,
    val content: String,
    val userId: UUID
)
