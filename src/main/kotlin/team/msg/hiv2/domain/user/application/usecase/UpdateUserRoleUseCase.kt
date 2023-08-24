package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class UpdateUserRoleUseCase(
    private val userService: UserService
) {
    fun execute(userId: UUID, role: UserRole) {
        val user = userService.queryUserById(userId)

        userService.save(user.copy(roles = mutableListOf(role)))
    }
}