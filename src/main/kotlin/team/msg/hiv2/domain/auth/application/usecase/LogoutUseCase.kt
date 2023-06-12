package team.msg.hiv2.domain.auth.application.usecase

import team.msg.hiv2.domain.auth.application.spi.RefreshTokenPort
import team.msg.hiv2.domain.auth.exception.RefreshTokenNotFoundException
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class LogoutUseCase(
    private val queryUserService: QueryUserService,
    private val refreshTokenPort: RefreshTokenPort
) {

    fun execute(refreshToken: String){
        val user = queryUserService.queryCurrentUser()
        val token = refreshTokenPort.queryByRefreshToken(refreshToken)
            ?: throw RefreshTokenNotFoundException()

        if(user.id != token.userId)
            throw UserNotFoundException()

        refreshTokenPort.delete(token)
    }
}