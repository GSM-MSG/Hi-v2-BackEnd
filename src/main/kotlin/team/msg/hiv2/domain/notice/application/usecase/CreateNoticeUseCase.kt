package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.spi.CommandNoticePort
import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.notice.presentation.data.request.CreateNoticeRequest
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.time.LocalDateTime
import java.util.*

@UseCase
class CreateNoticeUseCase(
    private val queryUserPort: QueryUserPort,
    private val commandNoticePort: CommandNoticePort
) {

    fun execute(request: CreateNoticeRequest) {
        val user = queryUserPort.queryCurrentUser()

        val notice = Notice(
            id = UUID.randomUUID(),
            title = request.title,
            content = request.content,
            userId = user.id,
            createdAt = LocalDateTime.now()
        )

        commandNoticePort.saveNotice(notice)
    }
}