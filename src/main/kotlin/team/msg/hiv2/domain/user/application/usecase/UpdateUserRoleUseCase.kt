package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserRoleWebRequest
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class UpdateUserRoleUseCase(
    private val userService: UserService
) {
    fun execute(userId: UUID, request: UpdateUserRoleWebRequest) {
        val user = userService.queryUserById(userId)

        userService.save(user.copy(roles = mutableListOf(request.role)))
    }
}