package team.msg.hiv2.domain.auth.presentation.data.web

import javax.validation.constraints.NotNull

data class GAuthSignInWebRequest(
    @field:NotNull
    val code: String
)
