package team.msg.hiv2.domain.notice.application.spi

import team.msg.hiv2.domain.notice.domain.Notice
import java.util.UUID

interface QueryNoticePort {

    fun queryAllNotice(): List<Notice>
    fun queryNoticeById(id: UUID): Notice?
}