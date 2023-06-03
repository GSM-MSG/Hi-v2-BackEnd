package team.msg.hiv2.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer{

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "HEAD", "DELETE", "OPTIONS")
            .allowedOrigins(
                "http://localhost:8080",
                "http://localhost:3000",
                "https://dev-hi.msg-team.com"
            )
    }
}