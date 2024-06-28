package team.msg.hiv2.domain.auth.presentation.data.web

import jakarta.validation.constraints.NotNull

data class GAuthSignInWebRequest(
    @field:NotNull
    val code: String
)
