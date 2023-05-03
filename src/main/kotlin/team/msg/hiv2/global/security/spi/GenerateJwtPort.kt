package team.msg.hiv2.global.security.spi

import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse

interface GenerateJwtPort {
    fun generate(email: String): TokenResponse
}