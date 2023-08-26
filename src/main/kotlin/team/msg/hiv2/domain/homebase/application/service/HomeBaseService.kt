package team.msg.hiv2.domain.homebase.application.service

import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class HomeBaseService(
    queryHomeBaseService: QueryHomeBaseService
) : QueryHomeBaseService by queryHomeBaseService