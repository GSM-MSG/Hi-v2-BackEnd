package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.spi.QueryNoticePort
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeResponse
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class QueryAllNoticeUseCase(
    private val queryNoticePort: QueryNoticePort,
    private val queryUserPort: QueryUserPort
) {
    fun execute(): List<NoticeResponse> =
        queryNoticePort.queryAllNotice()
            .map { NoticeResponse(
                title = it.title,
                content = it.content,
                user = getUser(it.userId)
            ) }


    private fun getUser(id: UUID): UserResponse =
        queryUserPort.queryUserById(id)
            .let { it ?: throw UserNotFoundException() }
            .let { UserResponse(it.id, it.name) }
}