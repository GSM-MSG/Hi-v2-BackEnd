package team.msg.hiv2.global.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration
import team.msg.hiv2.global.security.token.JwtProperties

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class
    ]
)
class PropertiesScanConfig {
}