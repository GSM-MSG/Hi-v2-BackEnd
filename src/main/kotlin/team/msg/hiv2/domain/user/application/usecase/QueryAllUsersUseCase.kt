package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.response.AllUsersResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryAllUsersUseCase(
    private val userService: UserService
) {

    fun execute(): AllUsersResponse {
        val users = userService.queryAllUsersOrderBySchoolNumber()

        return AllUsersResponse(
            users.map {
                UserResponse.of(it)
            }
        )
    }
}