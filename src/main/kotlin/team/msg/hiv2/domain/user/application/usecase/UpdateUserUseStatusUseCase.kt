package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class UpdateUserUseStatusUseCase(
    private val userService: UserService
) {
    fun execute(userId: UUID, status: UseStatus) {
        val user = userService.queryUserById(userId)

        userService.save(user.copy(useStatus = status))
    }
}