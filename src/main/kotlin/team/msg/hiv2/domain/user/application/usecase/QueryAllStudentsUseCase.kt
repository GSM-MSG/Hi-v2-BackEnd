package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.presentation.data.response.AllStudentsResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryAllStudentsUseCase(
    private val userService: UserService
) {
    fun execute(): List<AllStudentsResponse> {
        return userService.queryAllUserByUserRole(UserRole.ROLE_STUDENT)
            .map { AllStudentsResponse(
                userId = it.id,
                name = it.name,
                grade = it.grade,
                classNum = it.classNum,
                number = it.number,
                profileImageUrl = it.profileImageUrl,
                useStatus = it.useStatus
            ) }
    }
}