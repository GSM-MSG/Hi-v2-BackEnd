package team.msg.hiv2.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "HEAD", "DELETE", "OPTIONS")
            .allowedOrigins(
                "http://localhost:3000",
                "https://msg-hi.vercel.app/",
                "https://port-0-hi-v2-backend-p8xrq2mlfszgkzn.sel3.cloudtype.app/"
            )
            .allowCredentials(true)
    }
}