package team.msg.hiv2.domain.auth.application.usecase

import team.msg.hiv2.domain.auth.presentation.data.request.GAuthSignInRequest
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import team.msg.hiv2.domain.user.application.service.CommandUserService
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import team.msg.hiv2.global.security.spi.GenerateJwtPort
import team.msg.hiv2.thirdparty.gauth.spi.GAuthPort
import java.util.*

@UseCase
class GAuthSignInUseCase(
    private val gAuthPort: GAuthPort,
    private val queryUserService: QueryUserService,
    private val commandUserService: CommandUserService,
    private val generateJwtPort: GenerateJwtPort
) {

    fun execute(request: GAuthSignInRequest): TokenResponse{
        val gAuthToken = gAuthPort.queryGAuthToken(request.code)
        val gAuthUserInfo = gAuthPort.queryGAuthUserInfo(gAuthToken.accessToken)
        val role = queryUserService.queryUserRoleByEmail(gAuthUserInfo.email, gAuthUserInfo.role)

        val user = commandUserService.createUser(
            User(
                id = UUID.randomUUID(),
                email = gAuthUserInfo.email,
                name = gAuthUserInfo.name,
                grade = gAuthUserInfo.grade,
                classNum = gAuthUserInfo.classNum,
                number = gAuthUserInfo.num,
                profileImageUrl = gAuthUserInfo.profileUrl ?: "",
                roles = mutableListOf(role),
                reservationId = null,
                useStatus = UseStatus.AVAILABLE
            ),
            queryUserService.existsUserByEmail(gAuthUserInfo.email),
        )

        return generateJwtPort.generate(user.id, user.roles)
    }

}