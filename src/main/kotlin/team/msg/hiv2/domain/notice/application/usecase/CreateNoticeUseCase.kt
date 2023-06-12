package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.service.CommandNoticeService
import team.msg.hiv2.domain.notice.application.spi.CommandNoticePort
import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.notice.presentation.data.request.CreateNoticeRequest
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class CreateNoticeUseCase(
    private val queryUserService: QueryUserService,
    private val commandNoticeService: CommandNoticeService
) {

    fun execute(request: CreateNoticeRequest) {
        val user = queryUserService.queryCurrentUser()

        val notice = Notice(
            id = UUID.randomUUID(),
            title = request.title,
            content = request.content,
            userId = user.id
        )

        commandNoticeService.save(notice)
    }
}