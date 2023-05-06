package team.msg.hiv2.domain.auth.domain

import team.msg.hiv2.global.annotation.Aggregate
import java.util.UUID

@Aggregate
data class RefreshToken(
    val refreshToken: String,
    val userId: UUID,
    val expiredAt: Int
)
