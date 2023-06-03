package team.msg.hiv2.domain.auth.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.hiv2.domain.auth.application.usecase.GAuthSignInUseCase
import team.msg.hiv2.domain.auth.application.usecase.LogoutUseCase
import team.msg.hiv2.domain.auth.application.usecase.QueryGAuthLoginLinkUseCase
import team.msg.hiv2.domain.auth.application.usecase.ReissueTokenUseCase
import team.msg.hiv2.domain.auth.presentation.data.request.GAuthSignInRequest
import team.msg.hiv2.domain.auth.presentation.data.response.GAuthLinkResponse
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val gAuthSignInUseCase: GAuthSignInUseCase,
    private val reissueTokenUseCase: ReissueTokenUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val queryGAuthLoginLinkUseCase: QueryGAuthLoginLinkUseCase
) {

    @GetMapping
    fun queryGAuthLoginLink(): ResponseEntity<GAuthLinkResponse> =
        queryGAuthLoginLinkUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun signIn(@RequestBody @Valid request: GAuthSignInRequest): ResponseEntity<TokenResponse> =
        gAuthSignInUseCase.execute(request)
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun reissue(@RequestHeader("RefreshToken") @Valid refreshToken: String): ResponseEntity<TokenResponse> =
        reissueTokenUseCase.execute(refreshToken)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping
    fun logout(@RequestHeader("RefreshToken") @Valid refreshToken: String): ResponseEntity<Void> =
        logoutUseCase.execute(refreshToken)
            .let { ResponseEntity.noContent().build() }

}