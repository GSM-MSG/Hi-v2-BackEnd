package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.spi.QueryNoticePort
import team.msg.hiv2.domain.notice.exception.NoticeNotFoundException
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeResponse
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase
import java.util.UUID

@ReadOnlyUseCase
class QueryNoticeDetailsUseCase(
    private val queryNoticePort: QueryNoticePort,
    private val queryUserPort: QueryUserPort
) {
    fun execute(id: UUID): NoticeResponse {
        val notice = queryNoticePort.queryNoticeById(id)
            ?: throw NoticeNotFoundException()
        val user = queryUserPort.queryUserById(notice.userId)
            ?: throw UserNotFoundException()

        return NoticeResponse(
            noticeId = notice.id,
            title = notice.title,
            content = notice.content,
            user = UserResponse(
                userId = user.id,
                name = user.name
            ),
            createdAt = notice.createdAt
        )
    }
}