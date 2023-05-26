package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.spi.NoticePort
import team.msg.hiv2.domain.notice.exception.NoticeNotFoundException
import team.msg.hiv2.domain.notice.presentation.data.request.ModifyNoticeRequest
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class ModifyNoticeUseCase(
    private val noticePort: NoticePort,
    private val userValidator: UserValidator
) {
    fun execute(id: UUID, modifyNoticeRequest: ModifyNoticeRequest) {
        val notice = noticePort.queryNoticeById(id)
            ?: throw NoticeNotFoundException()

        userValidator.checkUserAndWriter(id, notice.userId)

        noticePort.saveNotice(notice.copy(title = modifyNoticeRequest.title, content = modifyNoticeRequest.content))
    }
}