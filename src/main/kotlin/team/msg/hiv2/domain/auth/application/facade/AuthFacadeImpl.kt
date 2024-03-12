package team.msg.hiv2.domain.auth.application.facade

import org.springframework.stereotype.Component
import team.msg.hiv2.domain.auth.application.usecase.GAuthSignInUseCase
import team.msg.hiv2.domain.auth.application.usecase.LogoutUseCase
import team.msg.hiv2.domain.auth.application.usecase.QueryGAuthLoginLinkUseCase
import team.msg.hiv2.domain.auth.application.usecase.ReissueTokenUseCase
import team.msg.hiv2.domain.auth.presentation.data.request.GAuthSignInRequest
import team.msg.hiv2.domain.auth.presentation.data.response.GAuthLinkResponse
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse

@Component
class AuthFacadeImpl(
    private val gAuthSignInUseCase: GAuthSignInUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val queryGAuthLoginLinkUseCase: QueryGAuthLoginLinkUseCase,
    private val reissueTokenUseCase: ReissueTokenUseCase
) : AuthFacade{

    override fun gAuthSignIn(request: GAuthSignInRequest): TokenResponse =
        gAuthSignInUseCase.execute(request)

    override fun logout(refreshToken: String) =
        logoutUseCase.execute(refreshToken)

    override fun reissue(requestToken: String): TokenResponse =
        reissueTokenUseCase.execute(requestToken)

    override fun queryGAuthLoginLink(): GAuthLinkResponse =
        queryGAuthLoginLinkUseCase.execute()
}