package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class SearchUserByNameKeywordUseCase(
    private val queryUserService: QueryUserService
) {

    fun execute(keyword: String): List<UserResponse> =
        queryUserService.queryUserByNameContaining(keyword).map {
            UserResponse(
                userId = it.id,
                name = it.name,
                grade = it.grade,
                classNum = it.classNum,
                number = it.number
            )
        }
}