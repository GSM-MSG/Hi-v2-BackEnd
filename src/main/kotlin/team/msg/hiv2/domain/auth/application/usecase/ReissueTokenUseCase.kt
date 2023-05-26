package team.msg.hiv2.domain.auth.application.usecase

import team.msg.hiv2.domain.auth.application.spi.RefreshTokenPort
import team.msg.hiv2.domain.auth.exception.InvalidRefreshTokenException
import team.msg.hiv2.domain.auth.exception.RefreshTokenNotFoundException
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.usecase.UseCase
import team.msg.hiv2.global.security.spi.GenerateJwtPort
import team.msg.hiv2.global.security.spi.JwtParserPort

@UseCase
class ReissueTokenUseCase(
    private val refreshTokenPort: RefreshTokenPort,
    private val userPort: UserPort,
    private val generateJwtPort: GenerateJwtPort,
    private val jwtParserPort: JwtParserPort
) {

    fun execute(requestToken: String): TokenResponse {
        val refreshToken = jwtParserPort.parseRefreshToken(requestToken)
            ?: throw InvalidRefreshTokenException()

        val token = refreshTokenPort.queryByRefreshToken(refreshToken)
            ?: throw RefreshTokenNotFoundException()

        val user = userPort.queryUserById(token.userId) ?: throw UserNotFoundException()
        return generateJwtPort.generate(user.id, user.roles)
    }
}