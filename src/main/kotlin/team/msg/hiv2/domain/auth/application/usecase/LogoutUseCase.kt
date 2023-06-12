package team.msg.hiv2.domain.auth.application.usecase

import team.msg.hiv2.domain.auth.application.service.CommandRefreshTokenService
import team.msg.hiv2.domain.auth.application.service.QueryRefreshTokenService
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class LogoutUseCase(
    private val queryUserService: QueryUserService,
    private val queryRefreshTokenService: QueryRefreshTokenService,
    private val commandRefreshTokenService: CommandRefreshTokenService
) {

    fun execute(refreshToken: String){
        val user = queryUserService.queryCurrentUser()
        val token = queryRefreshTokenService.queryByRefreshToken(refreshToken)

        if(user.id != token.userId)
            throw UserNotFoundException()

        commandRefreshTokenService.delete(token)
    }
}