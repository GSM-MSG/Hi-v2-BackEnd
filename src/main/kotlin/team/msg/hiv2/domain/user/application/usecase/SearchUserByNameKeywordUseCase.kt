package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.request.SearchUserKeywordRequest
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class SearchUserByNameKeywordUseCase(
    private val userService: UserService
) {

    fun execute(request: SearchUserKeywordRequest): List<UserResponse> =
        userService.queryUserByNameContaining(request.keyword).map {
            UserResponse(
                userId = it.id,
                name = it.name,
                grade = it.grade,
                classNum = it.classNum,
                number = it.number
            )
        }
}