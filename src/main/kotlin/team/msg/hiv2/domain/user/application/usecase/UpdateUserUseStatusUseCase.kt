package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserUseStatusRequest
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class UpdateUserUseStatusUseCase(
    private val userService: UserService
) {
    fun execute(userId: UUID, updateUserUseStatusRequest: UpdateUserUseStatusRequest) {
        val user = userService.queryUserById(userId)

        userService.save(user.copy(useStatus = updateUserUseStatusRequest.status))
    }
}