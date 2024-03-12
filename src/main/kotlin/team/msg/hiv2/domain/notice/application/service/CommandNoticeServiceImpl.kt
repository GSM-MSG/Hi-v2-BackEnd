package team.msg.hiv2.domain.notice.application.service

import team.msg.hiv2.domain.notice.application.spi.CommandNoticePort
import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class CommandNoticeServiceImpl(
    private val commandNoticePort: CommandNoticePort
) : CommandNoticeService {

    override fun save(notice: Notice): Notice =
        commandNoticePort.save(notice)

    override fun delete(notice: Notice) =
        commandNoticePort.delete(notice)

}