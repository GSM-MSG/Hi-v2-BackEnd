package team.msg.hiv2.domain.notice.application.spi

import java.util.UUID

interface QueryNoticePort {

    fun queryNotice()

    fun queryNoticeById(noticeId: UUID)
}