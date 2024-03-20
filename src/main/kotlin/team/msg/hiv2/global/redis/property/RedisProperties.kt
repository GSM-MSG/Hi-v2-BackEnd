package team.msg.hiv2.global.redis.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("spring.redis")
class RedisProperties(
    val host: String,
    val port: Int
) {
    companion object {
        const val REDISSON_HOST_PREFIX = "redis://"
    }
}