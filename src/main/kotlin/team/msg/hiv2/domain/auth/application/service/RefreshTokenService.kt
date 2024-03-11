package team.msg.hiv2.domain.auth.application.service

import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class RefreshTokenService(
    queryRefreshTokenService: QueryRefreshTokenService,
    commandRefreshTokenService: CommandRefreshTokenService
) : CommandRefreshTokenService by commandRefreshTokenService
    , QueryRefreshTokenService by queryRefreshTokenService