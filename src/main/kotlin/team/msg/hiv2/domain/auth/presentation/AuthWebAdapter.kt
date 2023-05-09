package team.msg.hiv2.domain.auth.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.hiv2.domain.auth.application.usecase.GAuthSignInUseCase
import team.msg.hiv2.domain.auth.application.usecase.ReissueTokenUseCase
import team.msg.hiv2.domain.auth.presentation.data.request.GAuthSignInRequest
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val gAuthSignInUseCase: GAuthSignInUseCase,
    private val reissueTokenUseCase: ReissueTokenUseCase
) {

    @PostMapping
    fun signIn(@RequestBody @Valid request: GAuthSignInRequest): ResponseEntity<TokenResponse> =
        gAuthSignInUseCase.execute(request)
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun reissue(@RequestHeader("RefreshToken") @Valid refreshToken: String): ResponseEntity<TokenResponse> =
        reissueTokenUseCase.execute(refreshToken)
            .let { ResponseEntity.ok(it) }
}