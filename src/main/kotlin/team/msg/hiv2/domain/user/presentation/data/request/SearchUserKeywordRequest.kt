package team.msg.hiv2.domain.user.presentation.data.request

import javax.validation.constraints.NotNull

data class SearchUserKeywordRequest(
    @field:NotNull
    val keyword: String
)