package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.spi.CommandNoticePort
import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.notice.presentation.data.request.CreateNoticeRequest
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class CreateNoticeUseCase(
    private val userPort: UserPort,
    private val commandNoticePort: CommandNoticePort
) {

    fun execute(request: CreateNoticeRequest) {
        val user = userPort.queryCurrentUser()

        val notice = Notice(
            id = UUID.randomUUID(),
            title = request.title,
            content = request.content,
            userId = user.id
        )

        val saveNotice = commandNoticePort.saveNotice(notice)
    }
}