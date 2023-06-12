package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.service.QueryNoticeService
import team.msg.hiv2.domain.notice.application.spi.QueryNoticePort
import team.msg.hiv2.domain.notice.exception.NoticeNotFoundException
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeDetailsResponse
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeResponse
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase
import java.util.UUID

@ReadOnlyUseCase
class QueryNoticeDetailsUseCase(
    private val queryUserService: QueryUserService,
    private val queryNoticeService: QueryNoticeService
) {
    fun execute(id: UUID): NoticeDetailsResponse {
        val notice = queryNoticeService.queryNoticeById(id)
        val user = queryUserService.queryUserById(notice.userId)

        return NoticeDetailsResponse(
            noticeId = notice.id,
            title = notice.title,
            content = notice.content,
            user = UserResponse(
                userId = user.id,
                name = user.name,
                grade = user.grade,
                classNum = user.classNum,
                number = user.number
            ),
            createdAt = notice.createdAt
        )
    }
}