package team.msg.hiv2.domain.auth.application.usecase

import team.msg.hiv2.domain.auth.application.service.QueryRefreshTokenService
import team.msg.hiv2.domain.auth.exception.InvalidRefreshTokenException
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.global.annotation.usecase.UseCase
import team.msg.hiv2.global.security.spi.GenerateJwtPort
import team.msg.hiv2.global.security.spi.JwtParserPort

@UseCase
class ReissueTokenUseCase(
    private val queryRefreshTokenService: QueryRefreshTokenService,
    private val queryUserService: QueryUserService,
    private val generateJwtPort: GenerateJwtPort,
    private val jwtParserPort: JwtParserPort
) {

    fun execute(requestToken: String): TokenResponse {
        val refreshToken = jwtParserPort.parseRefreshToken(requestToken)
            ?: throw InvalidRefreshTokenException()
        val token = queryRefreshTokenService.queryByRefreshToken(refreshToken)
        val user = queryUserService.queryUserById(token.userId)

        return generateJwtPort.generate(user.id, user.roles)
    }
}