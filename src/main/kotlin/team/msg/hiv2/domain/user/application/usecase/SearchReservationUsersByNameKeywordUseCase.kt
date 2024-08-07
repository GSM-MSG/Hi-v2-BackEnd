package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.request.SearchUserKeywordRequest
import team.msg.hiv2.domain.user.presentation.data.response.StudentResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class SearchReservationUsersByNameKeywordUseCase(
    private val userService: UserService
) {

    fun execute(request: SearchUserKeywordRequest): List<StudentResponse> {
        val users = userService.searchStudent(request.keyword)

        return users.map { StudentResponse.of(it) }
    }
}