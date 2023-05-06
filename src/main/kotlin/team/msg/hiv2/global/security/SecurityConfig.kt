package team.msg.hiv2.global.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.cors.CorsUtils
import team.msg.hiv2.global.config.FilterConfig
import team.msg.hiv2.global.security.handler.CustomAccessDenied
import team.msg.hiv2.global.security.handler.CustomAuthenticationEntryPoint
import team.msg.hiv2.global.security.spi.JwtParserPort

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtParserPort: JwtParserPort
) {

    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .cors()
            .and()
            .csrf().disable()
            .httpBasic().disable()

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .requestMatchers(RequestMatcher { request ->
                CorsUtils.isPreFlightRequest(request)
            }).permitAll()

            .antMatchers(HttpMethod.GET, "/").permitAll()

             // auth
            .antMatchers(HttpMethod.POST, "/auth").permitAll()

            .anyRequest().authenticated()
            .and()

            .exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .accessDeniedHandler(CustomAccessDenied())
            .and()

            .apply(FilterConfig(jwtParserPort))
            .and()
            .build()

    @Bean
    protected fun passwordEncoder() = BCryptPasswordEncoder()
}