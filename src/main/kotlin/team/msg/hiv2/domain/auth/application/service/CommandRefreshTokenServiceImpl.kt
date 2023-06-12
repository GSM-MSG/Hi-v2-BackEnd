package team.msg.hiv2.domain.auth.application.service

import team.msg.hiv2.domain.auth.application.spi.CommandRefreshTokenPort
import team.msg.hiv2.domain.auth.domain.RefreshToken
import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class CommandRefreshTokenServiceImpl(
    private val commandRefreshTokenPort: CommandRefreshTokenPort
) {
    fun save(refreshToken: RefreshToken): String =
        commandRefreshTokenPort.save(refreshToken)

    fun delete(refreshToken: RefreshToken) =
        commandRefreshTokenPort.delete(refreshToken)

}