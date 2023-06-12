package team.msg.hiv2.domain.auth.application.service

class RefreshTokenService(
    commandRefreshTokenService: CommandRefreshTokenService,
    queryRefreshTokenService: QueryRefreshTokenService
) : CommandRefreshTokenService by commandRefreshTokenService, QueryRefreshTokenService by queryRefreshTokenService{
}