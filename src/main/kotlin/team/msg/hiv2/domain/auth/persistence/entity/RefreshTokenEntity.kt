package team.msg.hiv2.domain.auth.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import team.msg.hiv2.domain.auth.domain.RefreshToken
import java.util.UUID
import java.util.concurrent.TimeUnit

@RedisHash("refresh_token")
data class RefreshTokenEntity(

    @Id
    val refreshToken: String,

    val userId: UUID,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Int

)

