package team.msg.hiv2.domain.user.presentation.data.web

import javax.validation.constraints.NotNull

data class SearchUserKeywordWebRequest(
    @field:NotNull
    val keyword: String
)
