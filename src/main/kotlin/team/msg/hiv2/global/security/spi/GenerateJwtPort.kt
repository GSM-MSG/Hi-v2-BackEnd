package team.msg.hiv2.global.security.spi

import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import team.msg.hiv2.domain.user.domain.constant.UserRole
import java.util.UUID

interface GenerateJwtPort {

    fun generate(userId: UUID, roles: MutableList<UserRole>): TokenResponse
}