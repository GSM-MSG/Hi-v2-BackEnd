package team.msg.hiv2.domain.auth.application.service

import team.msg.hiv2.domain.auth.application.spi.QueryRefreshTokenPort
import team.msg.hiv2.domain.auth.domain.RefreshToken
import team.msg.hiv2.domain.auth.exception.RefreshTokenNotFoundException
import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class QueryRefreshTokenServiceImpl(
    private val queryRefreshTokenPort: QueryRefreshTokenPort
) : QueryRefreshTokenPort {

    override fun queryByRefreshToken(refreshToken: String): RefreshToken =
        queryRefreshTokenPort.queryByRefreshToken(refreshToken) ?: throw RefreshTokenNotFoundException()
}