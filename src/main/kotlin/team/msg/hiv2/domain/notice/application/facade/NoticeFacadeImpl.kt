package team.msg.hiv2.domain.notice.application.facade

import org.springframework.stereotype.Component
import team.msg.hiv2.domain.notice.application.usecase.*
import team.msg.hiv2.domain.notice.presentation.data.request.CreateNoticeRequest
import team.msg.hiv2.domain.notice.presentation.data.request.UpdateNoticeRequest
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeDetailsResponse
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeResponse
import java.util.*

@Component
class NoticeFacadeImpl(
    private val createNoticeUseCase: CreateNoticeUseCase,
    private val deleteNoticeUseCase: DeleteNoticeUseCase,
    private val queryAllNoticeUseCase: QueryAllNoticeUseCase,
    private val queryNoticeDetailsUseCase: QueryNoticeDetailsUseCase,
    private val updateNoticeUseCase: UpdateNoticeUseCase
) : NoticeFacade {

    override fun createNotice(request: CreateNoticeRequest) =
        createNoticeUseCase.execute(request)

    override fun deleteNotice(id: UUID) =
        deleteNoticeUseCase.execute(id)

    override fun queryAllNotice(): List<NoticeResponse> =
        queryAllNoticeUseCase.execute()

    override fun queryNoticeDetails(id: UUID): NoticeDetailsResponse =
        queryNoticeDetailsUseCase.execute(id)

    override fun updateNotice(id: UUID, request: UpdateNoticeRequest) =
        updateNoticeUseCase.execute(id, request)

}