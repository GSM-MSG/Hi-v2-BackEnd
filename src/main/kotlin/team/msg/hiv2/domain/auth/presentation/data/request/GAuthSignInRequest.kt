package team.msg.hiv2.domain.auth.presentation.data.request

import javax.validation.constraints.NotNull

data class GAuthSignInRequest(
    val code: String
)
