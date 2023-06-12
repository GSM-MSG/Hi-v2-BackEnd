package team.msg.hiv2.domain.auth.application.service

import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class RefreshTokenService(
    commandRefreshTokenService: CommandRefreshTokenService,
    queryRefreshTokenService: QueryRefreshTokenService
) : CommandRefreshTokenService by commandRefreshTokenService,
    QueryRefreshTokenService by queryRefreshTokenService