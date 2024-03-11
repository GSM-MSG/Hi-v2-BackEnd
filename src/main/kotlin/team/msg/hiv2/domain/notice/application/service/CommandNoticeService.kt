package team.msg.hiv2.domain.notice.application.service

import team.msg.hiv2.domain.notice.domain.Notice

interface CommandNoticeService {
    fun save(notice: Notice): Notice
    fun delete(notice: Notice)
}