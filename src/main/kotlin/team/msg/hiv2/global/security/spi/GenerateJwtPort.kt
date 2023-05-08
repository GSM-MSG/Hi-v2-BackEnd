package team.msg.hiv2.global.security.spi

import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import java.util.UUID

interface GenerateJwtPort {
    fun generate(userId: UUID): TokenResponse
}