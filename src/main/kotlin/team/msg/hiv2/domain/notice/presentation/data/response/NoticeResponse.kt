package team.msg.hiv2.domain.notice.presentation.data.response

import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import java.time.LocalDateTime
import java.util.*

data class NoticeResponse(
    val noticeId: UUID,
    val title: String,
    val content: String,
    val user: UserResponse,
    val createdAt: LocalDateTime
)