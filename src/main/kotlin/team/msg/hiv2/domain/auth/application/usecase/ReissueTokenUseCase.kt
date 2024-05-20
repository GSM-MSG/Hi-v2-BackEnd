package team.msg.hiv2.domain.auth.application.usecase

import team.msg.hiv2.domain.auth.application.service.RefreshTokenService
import team.msg.hiv2.domain.auth.exception.InvalidRefreshTokenException
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.global.annotation.usecase.UseCase
import team.msg.hiv2.global.security.spi.GenerateJwtPort
import team.msg.hiv2.global.security.spi.JwtParserPort

@UseCase
class ReissueTokenUseCase(
    private val refreshTokenService: RefreshTokenService,
    private val userService: UserService,
    private val generateJwtPort: GenerateJwtPort,
    private val jwtParserPort: JwtParserPort
) {

    fun execute(requestToken: String): TokenResponse {
        val refreshToken = jwtParserPort.parseRefreshToken(requestToken)
            ?: throw InvalidRefreshTokenException()

        val token = refreshTokenService.queryByRefreshToken(refreshToken)

        val user = userService.queryUserById(token.userId)

        refreshTokenService.deleteById(refreshToken)
        return generateJwtPort.generate(user.id, user.role)
    }
}