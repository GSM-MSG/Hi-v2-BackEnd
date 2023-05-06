package team.msg.hiv2.domain.auth.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.msg.hiv2.domain.auth.application.usecase.GAuthSignInUseCase
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val gAuthSignInUseCase: GAuthSignInUseCase
) {

    @PostMapping
    fun signIn(@RequestParam code: String): ResponseEntity<TokenResponse> =
        gAuthSignInUseCase.execute(code)
            .let { ResponseEntity.ok(it) }
}