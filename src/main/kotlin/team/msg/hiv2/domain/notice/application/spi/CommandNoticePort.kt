package team.msg.hiv2.domain.notice.application.spi

import team.msg.hiv2.domain.notice.domain.Notice

interface CommandNoticePort {
    fun saveNotice(notice: Notice)

    fun deleteNotice(notice: Notice)
}