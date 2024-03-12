package team.msg.hiv2.global.config

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import team.msg.hiv2.global.filter.ExceptionFilter
import team.msg.hiv2.global.filter.JwtRequestFilter
import team.msg.hiv2.global.security.spi.JwtParserPort

@Component
class FilterConfig(
    private val jwtParserPort: JwtParserPort
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(JwtRequestFilter(jwtParserPort), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ExceptionFilter(), JwtRequestFilter::class.java)
    }
}