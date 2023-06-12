package team.msg.hiv2.domain.auth.application.usecase

<<<<<<< HEAD
import team.msg.hiv2.domain.auth.application.service.CommandRefreshTokenService
import team.msg.hiv2.domain.auth.application.service.QueryRefreshTokenService
=======
import team.msg.hiv2.domain.auth.application.spi.RefreshTokenPort
import team.msg.hiv2.domain.auth.exception.RefreshTokenNotFoundException
>>>>>>> parent of 6c52165 (refactor :: auth usecase 서비스 분리)
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class LogoutUseCase(
    private val queryUserService: QueryUserService,
<<<<<<< HEAD
    private val queryRefreshTokenService: QueryRefreshTokenService,
    private val commandRefreshTokenService: CommandRefreshTokenService
=======
    private val refreshTokenPort: RefreshTokenPort
>>>>>>> parent of 6c52165 (refactor :: auth usecase 서비스 분리)
) {

    fun execute(refreshToken: String){
        val user = queryUserService.queryCurrentUser()
<<<<<<< HEAD
        val token = queryRefreshTokenService.queryByRefreshToken(refreshToken)
=======
        val token = refreshTokenPort.queryByRefreshToken(refreshToken)
            ?: throw RefreshTokenNotFoundException()
>>>>>>> parent of 6c52165 (refactor :: auth usecase 서비스 분리)

        if(user.id != token.userId)
            throw UserNotFoundException()

<<<<<<< HEAD
        commandRefreshTokenService.delete(token)
=======
        refreshTokenPort.delete(token)
>>>>>>> parent of 6c52165 (refactor :: auth usecase 서비스 분리)
    }
}