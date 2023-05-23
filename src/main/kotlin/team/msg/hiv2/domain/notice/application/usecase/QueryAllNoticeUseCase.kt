package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.spi.QueryNoticePort
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeResponse
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class QueryAllNoticeUseCase(
    private val queryNoticePort: QueryNoticePort,
    private val queryUserPort: QueryUserPort
) {
    fun execute(): List<NoticeResponse> {
        val notices = queryNoticePort.queryAllNotice()

        return notices.map {
            val user = queryUserPort.queryUserByNotice(it.id)
            NoticeResponse(
                noticeId = it.id,
                title = it.title,
                content = it.content,
                user = UserResponse(
                    userId = user.id,
                    name = user.name
                ),
                createdAt = it.createdAt
            )
        }
    }
}