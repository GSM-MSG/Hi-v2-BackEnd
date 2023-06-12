package team.msg.hiv2.domain.homebase.application.service

import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class HomeBaseService(
    private val commandHomeBaseService: CommandHomeBaseService,
    private val queryHomeBaseService: QueryHomeBaseService
) : CommandHomeBaseService by commandHomeBaseService, QueryHomeBaseService by queryHomeBaseService{
}