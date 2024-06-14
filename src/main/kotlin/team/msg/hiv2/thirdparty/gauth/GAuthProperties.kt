package team.msg.hiv2.thirdparty.gauth

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "gauth")
data class GAuthProperties(
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String
)