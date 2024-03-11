package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class DeleteNoticeUseCase(
    private val noticeService: NoticeService
) {
    fun execute(id: UUID) {
        val notice = noticeService.queryNoticeById(id)

        noticeService.delete(notice)
    }
}