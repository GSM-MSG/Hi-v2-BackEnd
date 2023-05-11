package team.msg.hiv2.domain.notice.application.spi

import team.msg.hiv2.domain.notice.domain.Notice
import java.util.UUID

interface CommandNoticePort {

    fun saveNotice(notice: Notice)
    fun deleteNotice(noticeId: UUID)
}