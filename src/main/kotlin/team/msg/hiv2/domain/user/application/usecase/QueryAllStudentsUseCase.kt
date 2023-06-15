package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.presentation.data.response.AllStudentsResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryAllStudentsUseCase(
    private val queryUserPort: QueryUserPort
) {
    fun execute(): List<AllStudentsResponse> {
        val users = queryUserPort.queryAllUser()
        return users.asSequence().filter {
            return@filter it.roles.firstOrNull() == UserRole.ROLE_STUDENT
        }.map {
                AllStudentsResponse(
                    userId = it.id,
                    name = it.name,
                    grade = it.grade,
                    classNum = it.classNum,
                    number = it.number,
                    profileImageUrl = it.profileImageUrl,
                    useStatus = it.useStatus
                )
        }.toList()
    }
}