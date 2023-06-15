package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.notice.presentation.data.request.CreateNoticeRequest
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class CreateNoticeUseCase(
    private val userService: UserService,
    private val noticeService: NoticeService
) {

    fun execute(request: CreateNoticeRequest) {
        val user = userService.queryCurrentUser()

        val notice = Notice(
            id = UUID.randomUUID(),
            title = request.title,
            content = request.content,
            userId = user.id
        )

        noticeService.save(notice)
    }
}