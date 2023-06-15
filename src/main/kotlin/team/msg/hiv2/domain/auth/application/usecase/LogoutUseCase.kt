package team.msg.hiv2.domain.auth.application.usecase

import team.msg.hiv2.domain.auth.application.service.RefreshTokenService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class LogoutUseCase(
    private val userService: UserService,
    private val refreshTokenService: RefreshTokenService
) {

    fun execute(refreshToken: String){
        val user = userService.queryCurrentUser()
        val token = refreshTokenService.queryByRefreshToken(refreshToken)

        if(user.id != token.userId)
            throw UserNotFoundException()

        refreshTokenService.delete(token)
    }
}