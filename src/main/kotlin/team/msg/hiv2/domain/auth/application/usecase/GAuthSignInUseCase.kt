package team.msg.hiv2.domain.auth.application.usecase

import team.msg.hiv2.domain.auth.presentation.data.request.GAuthSignInRequest
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.usecase.UseCase
import team.msg.hiv2.global.security.spi.GenerateJwtPort
import team.msg.hiv2.thirdparty.gauth.spi.GAuthPort
import java.util.*

@UseCase
class GAuthSignInUseCase(
    private val userPort: UserPort,
    private val gAuthPort: GAuthPort,
    private val generateJwtPort: GenerateJwtPort
) {

    fun execute(request: GAuthSignInRequest): TokenResponse{
        val gAuthToken = gAuthPort.queryGAuthToken(request.code)
        val gAuthUserInfo = gAuthPort.queryGAuthUserInfo(gAuthToken.accessToken)
        val role = userPort.queryUserRoleByEmail(gAuthUserInfo.email, gAuthUserInfo.role)

        val user = createUser(
            userPort.existsUserByEmail(gAuthUserInfo.email),
            User(
                id = UUID.randomUUID(),
                email = gAuthUserInfo.email,
                name = gAuthUserInfo.name,
                grade = gAuthUserInfo.grade,
                classNum = gAuthUserInfo.classNum,
                number = gAuthUserInfo.num,
                profileImageUrl = gAuthUserInfo.profileUrl,
                roles = mutableListOf(role)
            )
        )

        return generateJwtPort.generate(user.id, user.roles)
    }

    private fun createUser(isExistUser: Boolean, user: User): User {
        return if(isExistUser){
            userPort.queryUserByEmail(user.email) ?: throw UserNotFoundException()
        } else {
            userPort.save(user)!!
        }
    }
}