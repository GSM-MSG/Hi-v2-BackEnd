package team.msg.hiv2.domain.user.application.service

import team.msg.hiv2.global.annotation.service.DomainService


@DomainService
class UserService(
    commandUserService: CommandUserService,
    queryUserService: QueryUserService
) : CommandUserService by commandUserService,
    QueryUserService by queryUserService