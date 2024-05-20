package team.msg.hiv2.domain.auth.domain

import javax.persistence.Id
import team.msg.hiv2.global.annotation.Aggregate
import java.util.UUID

@Aggregate
data class RefreshToken(
    @Id
    val refreshToken: String,
    val userId: UUID,
    val expiredAt: Int
)
