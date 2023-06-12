package team.msg.hiv2.domain.auth.application.usecase

import team.msg.hiv2.domain.auth.application.service.QueryRefreshTokenService
import team.msg.hiv2.domain.auth.application.service.RefreshTokenService
import team.msg.hiv2.domain.auth.application.spi.RefreshTokenPort
import team.msg.hiv2.domain.auth.exception.RefreshTokenNotFoundException
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class LogoutUseCase(
    private val queryUserService: QueryUserService,
    private val refreshTokenService: RefreshTokenService
) {

    fun execute(refreshToken: String){
        val user = queryUserService.queryCurrentUser()
        val token = refreshTokenService.queryByRefreshToken(refreshToken)

        if(user.id != token.userId)
            throw UserNotFoundException()

        refreshTokenService.delete(token)
    }
}