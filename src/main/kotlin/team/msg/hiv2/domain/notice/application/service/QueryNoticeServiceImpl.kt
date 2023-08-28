package team.msg.hiv2.domain.notice.application.service

import team.msg.hiv2.domain.notice.application.spi.QueryNoticePort
import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.notice.exception.NoticeNotFoundException
import team.msg.hiv2.global.annotation.service.DomainService
import java.util.*

@DomainService
class QueryNoticeServiceImpl(
    private val queryNoticePort: QueryNoticePort
) : QueryNoticeService {

    override fun queryAllNoticeByOrderByCreatedAtDesc(): List<Notice> =
        queryNoticePort.queryAllNoticeByOrderByCreatedAtDesc()

    override fun queryNoticeById(id: UUID): Notice =
        queryNoticePort.queryNoticeById(id) ?: throw NoticeNotFoundException()
}