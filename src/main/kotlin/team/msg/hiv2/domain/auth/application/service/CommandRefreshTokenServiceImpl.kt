package team.msg.hiv2.domain.auth.application.service

import team.msg.hiv2.domain.auth.application.spi.CommandRefreshTokenPort
import team.msg.hiv2.domain.auth.domain.RefreshToken
import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class CommandRefreshTokenServiceImpl(
    private val commandRefreshTokenPort: CommandRefreshTokenPort
) : CommandRefreshTokenService {

    override fun save(refreshToken: RefreshToken): String =
        commandRefreshTokenPort.save(refreshToken)

    override fun delete(refreshToken: RefreshToken) =
        commandRefreshTokenPort.delete(refreshToken)

    override fun deleteById(refreshToken: String) =
        commandRefreshTokenPort.deleteById(refreshToken)

}