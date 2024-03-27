package team.msg.hiv2.global.redis

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import team.msg.hiv2.global.redis.RedisProperties.Companion.REDISSON_HOST_PREFIX


@Configuration
class RedisConfig(
    private val redisProperties: RedisProperties
) {

    @Bean
    fun redissonClient(): RedissonClient {
        val config = Config()
        config.useSingleServer().setAddress("$REDISSON_HOST_PREFIX ${redisProperties.host} : ${redisProperties.port}")

        return Redisson.create(config)
    }
}