package team.msg.hiv2.domain.auth.application.facade

import team.msg.hiv2.domain.auth.presentation.data.request.GAuthSignInRequest
import team.msg.hiv2.domain.auth.presentation.data.response.GAuthLinkResponse
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse

interface AuthFacade {
    fun gAuthSignIn(request: GAuthSignInRequest): TokenResponse
    fun logout(refreshToken: String)
    fun reissue(requestToken: String): TokenResponse
    fun queryGAuthLoginLink(): GAuthLinkResponse
}