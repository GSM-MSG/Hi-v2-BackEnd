package team.msg.hiv2.domain.auth.adapter.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import team.msg.hiv2.domain.auth.domain.RefreshToken
import java.util.concurrent.TimeUnit

@RedisHash("refresh_token")
data class RefreshTokenEntity(

    @Id
    val refreshToken: String,

    val email: String,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Int

)

fun RefreshTokenEntity.toDomain() = RefreshToken(refreshToken = refreshToken, email = email, expiredAt = expiredAt)