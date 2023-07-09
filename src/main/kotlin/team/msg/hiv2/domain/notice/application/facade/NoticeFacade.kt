package team.msg.hiv2.domain.notice.application.facade

import team.msg.hiv2.domain.notice.presentation.data.request.CreateNoticeRequest
import team.msg.hiv2.domain.notice.presentation.data.request.UpdateNoticeRequest
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeDetailsResponse
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeResponse
import java.util.*

interface NoticeFacade {
    fun createNotice(request: CreateNoticeRequest)
    fun deleteNotice(id: UUID)
    fun queryAllNotice(): List<NoticeResponse>
    fun queryNoticeDetails(id: UUID): NoticeDetailsResponse
    fun updateNotice(id: UUID, request: UpdateNoticeRequest)
}