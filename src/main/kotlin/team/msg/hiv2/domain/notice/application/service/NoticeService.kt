package team.msg.hiv2.domain.notice.application.service

import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class NoticeService(
    queryNoticeService: QueryNoticeService,
    commandNoticeService: CommandNoticeService
) : QueryNoticeService by queryNoticeService, CommandNoticeService by commandNoticeService