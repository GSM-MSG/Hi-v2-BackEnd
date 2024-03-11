package team.msg.hiv2.domain.notice.application.spi

import team.msg.hiv2.domain.notice.domain.Notice

interface CommandNoticePort {

    fun save(notice: Notice): Notice
    fun delete(notice: Notice)
}