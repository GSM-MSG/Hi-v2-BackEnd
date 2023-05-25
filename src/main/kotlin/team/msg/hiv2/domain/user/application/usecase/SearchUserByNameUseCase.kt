package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class SearchUserByNameUseCase(
    private val queryUserPort: QueryUserPort
) {

    fun execute(keyword: String): List<UserResponse> =
        queryUserPort.queryUserByNameContaining(keyword).map {
            UserResponse(it.id, it.name)
        }
}