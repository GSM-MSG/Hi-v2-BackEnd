package team.msg.hiv2.domain.notice.domain

import team.msg.hiv2.global.annotation.Aggregate
import java.time.LocalDateTime
import java.util.UUID

@Aggregate
data class Notice(
    val id: UUID,
    val title: String,
    val content: String,
    val userId: UUID,
    val createdAt: LocalDateTime
)
