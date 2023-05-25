package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.spi.QueryNoticePort
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeResponse
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryAllNoticeUseCase(
    private val queryNoticePort: QueryNoticePort,
    private val queryUserPort: QueryUserPort
) {
    fun execute(): List<NoticeResponse> {
        val notices = queryNoticePort.queryAllNotice()

        return notices.map {
            val user = queryUserPort.queryUserById(it.userId)
                ?: throw UserNotFoundException()
            NoticeResponse(
                noticeId = it.id,
                title = it.title,
                user = UserResponse(
                    userId = user.id,
                    name = user.name,
                    grade = user.grade,
                    classNum = user.classNum,
                    number = user.number
                ),
                createdAt = it.createdAt
            )
        }
    }
}