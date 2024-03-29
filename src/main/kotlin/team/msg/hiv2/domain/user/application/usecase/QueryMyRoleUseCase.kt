package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.response.UserRoleResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryMyRoleUseCase(
    private val userService: UserService
) {
    fun execute(): UserRoleResponse {
        val user = userService.queryCurrentUser()

        return UserRoleResponse.of(user.id, user.role)
    }
}