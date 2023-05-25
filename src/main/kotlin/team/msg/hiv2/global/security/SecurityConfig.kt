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
import team.msg.hiv2.domain.user.persistence.repository.UserRepository
import team.msg.hiv2.global.config.FilterConfig
import team.msg.hiv2.global.security.handler.CustomAccessDenied
import team.msg.hiv2.global.security.handler.CustomAuthenticationEntryPoint
import team.msg.hiv2.global.security.spi.JwtParserPort

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtParserPort: JwtParserPort, private val userRepository: UserRepository
) {

    companion object {
        const val STUDENT = "STUDENT"
        const val TEACHER = "TEACHER"
        const val ADMIN = "ADMIN"
    }

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

             // health check
            .antMatchers(HttpMethod.GET, "/").permitAll()

             // auth
            .antMatchers(HttpMethod.POST, "/auth").permitAll()
            .antMatchers(HttpMethod.PATCH, "/auth").permitAll()

             // homeBase
            .antMatchers(HttpMethod.POST, "/homebase").hasRole(STUDENT)
            .antMatchers(HttpMethod.GET, "/homebase").authenticated()

             // reservation
            .antMatchers(HttpMethod.GET, "/reservation/{reservation_id}").authenticated()
            .antMatchers(HttpMethod.DELETE, "/reservation/{reservation_id}").hasRole(STUDENT)
            .antMatchers(HttpMethod.PATCH, "/reservation/{reservation_id}").hasRole(STUDENT)
            .antMatchers(HttpMethod.PATCH, "/reservation/{reservation_id}/{user_id}").hasRole(STUDENT)
            .antMatchers(HttpMethod.DELETE, "/reservation/{reservation_id}/exit").hasRole(STUDENT)
            .antMatchers(HttpMethod.PATCH, "/reservation/{reservation_id}/check-status").hasRole(TEACHER)

             // notice
            .antMatchers(HttpMethod.GET, "/notice").authenticated()
            .antMatchers(HttpMethod.POST, "/notice").hasAnyRole(ADMIN, TEACHER)

             // user
            .antMatchers(HttpMethod.GET, "/user").authenticated()

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