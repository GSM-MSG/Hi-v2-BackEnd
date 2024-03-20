package team.msg.hiv2.global.redis.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import team.msg.hiv2.global.redis.property.RedisProperties

@Configuration
class RedisConfig(
    private val redisProperties: RedisProperties
) {

    @Bean
    fun redissonClient(): RedissonClient {
        val config = Config()
        Config().useSingleServer()
            .setAddress("${RedisProperties.REDISSON_HOST_PREFIX}${redisProperties.host}:${redisProperties.port}")
        return Redisson.create(config)
    }
}