package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.spi.NoticePort
import team.msg.hiv2.domain.notice.exception.NoticeNotFoundException
import team.msg.hiv2.domain.notice.presentation.data.request.UpdateNoticeRequest
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class UpdateNoticeUseCase(
    private val noticePort: NoticePort,
    private val userValidator: UserValidator
) {
    fun execute(id: UUID, updateNoticeRequest: UpdateNoticeRequest) {
        val notice = noticePort.queryNoticeById(id)
            ?: throw NoticeNotFoundException()

        userValidator.checkUserAndWriter(id, notice.userId)

        noticePort.saveNotice(notice.copy(title = updateNoticeRequest.title, content = updateNoticeRequest.content))
    }
}