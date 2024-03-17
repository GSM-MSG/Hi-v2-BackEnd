package team.msg.hiv2.domain.notice.application.service

import team.msg.hiv2.domain.notice.domain.Notice
import java.util.*

interface QueryNoticeService {
    fun queryAllNoticeByOrderByCreatedAtDesc(): List<Notice>
    fun queryNoticeById(id: UUID): Notice
}