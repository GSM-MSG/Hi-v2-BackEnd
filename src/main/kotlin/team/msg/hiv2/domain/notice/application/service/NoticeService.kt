package team.msg.hiv2.domain.notice.application.service

class NoticeService(
    commandNoticeService: CommandNoticeService,
    queryNoticeService: QueryNoticeService
) : CommandNoticeService by commandNoticeService, QueryNoticeService by queryNoticeService {
}