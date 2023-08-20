package team.msg.hiv2.domain.notice.presentation.data.response

import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import java.time.LocalDateTime
import java.util.*

data class NoticeDetailsResponse (
    val noticeId: UUID,
    val title: String,
    val content: String,
    val user: UserResponse,
    val createdAt: LocalDateTime
) {
    companion object {
        fun of(notice: Notice, user: UserResponse) = NoticeDetailsResponse(
            noticeId = notice.id,
            title = notice.title,
            content = notice.content,
            user = user,
            createdAt = notice.createdAt
        )
    }
}