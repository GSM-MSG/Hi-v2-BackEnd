package team.msg.hiv2.domain.auth.application.usecase

<<<<<<< HEAD
import team.msg.hiv2.domain.auth.application.service.QueryRefreshTokenService
=======
import team.msg.hiv2.domain.auth.application.spi.RefreshTokenPort
>>>>>>> parent of 6c52165 (refactor :: auth usecase 서비스 분리)
import team.msg.hiv2.domain.auth.exception.InvalidRefreshTokenException
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
<<<<<<< HEAD
import team.msg.hiv2.domain.user.application.service.QueryUserService
=======
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.domain.user.exception.UserNotFoundException
>>>>>>> parent of 6c52165 (refactor :: auth usecase 서비스 분리)
import team.msg.hiv2.global.annotation.usecase.UseCase
import team.msg.hiv2.global.security.spi.GenerateJwtPort
import team.msg.hiv2.global.security.spi.JwtParserPort

@UseCase
class ReissueTokenUseCase(
<<<<<<< HEAD
    private val queryRefreshTokenService: QueryRefreshTokenService,
    private val queryUserService: QueryUserService,
=======
    private val refreshTokenPort: RefreshTokenPort,
    private val userPort: UserPort,
>>>>>>> parent of 6c52165 (refactor :: auth usecase 서비스 분리)
    private val generateJwtPort: GenerateJwtPort,
    private val jwtParserPort: JwtParserPort
) {

    fun execute(requestToken: String): TokenResponse {
        val refreshToken = jwtParserPort.parseRefreshToken(requestToken)
            ?: throw InvalidRefreshTokenException()
<<<<<<< HEAD
        val token = queryRefreshTokenService.queryByRefreshToken(refreshToken)
        val user = queryUserService.queryUserById(token.userId)
=======
>>>>>>> parent of 6c52165 (refactor :: auth usecase 서비스 분리)

        val token = refreshTokenPort.queryByRefreshToken(refreshToken)
            ?: throw RefreshTokenNotFoundException()

        val user = userPort.queryUserById(token.userId) ?: throw UserNotFoundException()
        return generateJwtPort.generate(user.id, user.roles)
    }
}