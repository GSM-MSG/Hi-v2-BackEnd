package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.presentation.data.response.AllUserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryAllUsersUseCase(
    private val queryUserPort: QueryUserPort
) {
    fun execute(): List<AllUserResponse> {
        val users = queryUserPort.queryAllUser()
        return users
            .map {
                AllUserResponse(
                    userId = it.id,
                    name = it.name,
                    grade = it.grade,
                    classNum = it.classNum,
                    number = it.number,
                    useStatus = it.useStatus
                )
            }
    }
}