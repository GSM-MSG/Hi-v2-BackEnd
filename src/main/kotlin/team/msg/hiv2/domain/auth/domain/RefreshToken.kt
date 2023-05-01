package team.msg.hiv2.domain.auth.domain

import team.msg.hiv2.global.annotation.Aggregate

@Aggregate
data class RefreshToken(
    val refreshToken: String,
    val email: String,
    val expiredAt: Int
)
