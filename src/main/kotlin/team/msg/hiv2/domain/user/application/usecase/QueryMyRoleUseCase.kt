package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.domain.user.exception.UserRoleNotFoundException
import team.msg.hiv2.domain.user.presentation.data.response.UserRoleResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase
import team.msg.hiv2.global.security.spi.SecurityPort

@ReadOnlyUseCase
class QueryMyRoleUseCase(
    private val queryUserPort: QueryUserPort,
    private val securityPort: SecurityPort
) {
    fun execute(): UserRoleResponse {
        val user = securityPort.queryCurrentUserId().let {
            queryUserPort.queryUserById(it) ?: throw UserNotFoundException()
        }

        val role = user.roles.firstOrNull() ?: throw UserRoleNotFoundException()

        return UserRoleResponse.of(role)
    }
}