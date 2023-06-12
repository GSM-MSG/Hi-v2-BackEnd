package team.msg.hiv2.domain.auth.application.usecase

<<<<<<< HEAD
<<<<<<< HEAD
import team.msg.hiv2.domain.auth.application.service.CommandRefreshTokenService
import team.msg.hiv2.domain.auth.application.service.QueryRefreshTokenService
=======
import team.msg.hiv2.domain.auth.application.spi.RefreshTokenPort
import team.msg.hiv2.domain.auth.exception.RefreshTokenNotFoundException
>>>>>>> parent of 6c52165 (refactor :: auth usecase 서비스 분리)
=======
import team.msg.hiv2.domain.auth.application.service.CommandRefreshTokenService
import team.msg.hiv2.domain.auth.application.service.QueryRefreshTokenService
>>>>>>> 7e037235f7a69807912bee1d5a4fed33829d939f
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class LogoutUseCase(
    private val queryUserService: QueryUserService,
<<<<<<< HEAD
<<<<<<< HEAD
    private val queryRefreshTokenService: QueryRefreshTokenService,
    private val commandRefreshTokenService: CommandRefreshTokenService
=======
    private val refreshTokenPort: RefreshTokenPort
>>>>>>> parent of 6c52165 (refactor :: auth usecase 서비스 분리)
=======
    private val queryRefreshTokenService: QueryRefreshTokenService,
    private val commandRefreshTokenService: CommandRefreshTokenService
>>>>>>> 7e037235f7a69807912bee1d5a4fed33829d939f
) {

    fun execute(refreshToken: String){
        val user = queryUserService.queryCurrentUser()
<<<<<<< HEAD
<<<<<<< HEAD
        val token = queryRefreshTokenService.queryByRefreshToken(refreshToken)
=======
        val token = refreshTokenPort.queryByRefreshToken(refreshToken)
            ?: throw RefreshTokenNotFoundException()
>>>>>>> parent of 6c52165 (refactor :: auth usecase 서비스 분리)
=======
        val token = queryRefreshTokenService.queryByRefreshToken(refreshToken)
>>>>>>> 7e037235f7a69807912bee1d5a4fed33829d939f

        if(user.id != token.userId)
            throw UserNotFoundException()

<<<<<<< HEAD
<<<<<<< HEAD
        commandRefreshTokenService.delete(token)
=======
        refreshTokenPort.delete(token)
>>>>>>> parent of 6c52165 (refactor :: auth usecase 서비스 분리)
=======
        commandRefreshTokenService.delete(token)
>>>>>>> 7e037235f7a69807912bee1d5a4fed33829d939f
    }
}