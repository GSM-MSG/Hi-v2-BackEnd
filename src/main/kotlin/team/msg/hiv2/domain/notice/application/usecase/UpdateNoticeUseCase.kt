package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.notice.presentation.data.request.UpdateNoticeRequest
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class UpdateNoticeUseCase(
    private val noticeService: NoticeService
) {
    fun execute(id: UUID, updateNoticeRequest: UpdateNoticeRequest) {
        val notice = noticeService.queryNoticeById(id)

        noticeService.save(notice.copy(title = updateNoticeRequest.title, content = updateNoticeRequest.content))
    }
}