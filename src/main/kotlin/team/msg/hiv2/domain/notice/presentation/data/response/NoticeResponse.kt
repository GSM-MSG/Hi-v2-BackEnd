package team.msg.hiv2.domain.notice.presentation.data.response

import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.user.presentation.data.response.NoticeUserResponse
import java.time.LocalDateTime
import java.util.*

data class NoticeResponse(
    val noticeId: UUID,
    val title: String,
    val user: NoticeUserResponse,
    val index: Long,
    val createdAt: LocalDateTime
) {
    companion object {
        fun of(notice: Notice, user: NoticeUserResponse, index: Long) = NoticeResponse(
            noticeId = notice.id,
            title = notice.title,
            user = user,
            index = index,
            createdAt = notice.createdAt
        )
    }
}