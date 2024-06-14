package team.msg.hiv2.domain.auth.presentation

import javax.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.hiv2.domain.auth.application.facade.AuthFacade
import team.msg.hiv2.domain.auth.presentation.data.request.GAuthSignInRequest
import team.msg.hiv2.domain.auth.presentation.data.response.GAuthLinkResponse
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import team.msg.hiv2.domain.auth.presentation.data.web.GAuthSignInWebRequest

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val authFacade: AuthFacade
) {

    @GetMapping
    fun queryGAuthLoginLink(): ResponseEntity<GAuthLinkResponse> =
        authFacade.queryGAuthLoginLink()
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun signIn(@RequestBody @Valid request: GAuthSignInWebRequest): ResponseEntity<TokenResponse> =
        authFacade.gAuthSignIn(
            GAuthSignInRequest(request.code)
        )
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun reissue(@RequestHeader("RefreshToken") @Valid refreshToken: String): ResponseEntity<TokenResponse> =
        authFacade.reissue(refreshToken)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping
    fun logout(@RequestHeader("RefreshToken") @Valid refreshToken: String): ResponseEntity<Unit> =
        authFacade.logout(refreshToken)
            .let { ResponseEntity.noContent().build() }

}