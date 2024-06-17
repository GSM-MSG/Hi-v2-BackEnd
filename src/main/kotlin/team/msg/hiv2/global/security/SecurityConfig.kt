package team.msg.hiv2.global.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.cors.CorsUtils
import team.msg.hiv2.global.filter.ExceptionFilter
import team.msg.hiv2.global.filter.JwtRequestFilter
import team.msg.hiv2.global.security.handler.CustomAccessDenied
import team.msg.hiv2.global.security.handler.CustomAuthenticationEntryPoint
import team.msg.hiv2.global.security.spi.JwtParserPort

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtParserPort: JwtParserPort
) {

    companion object {
        const val STUDENT = "STUDENT"
        const val TEACHER = "TEACHER"
        const val ADMIN = "ADMIN"
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.disable() }
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it.requestMatchers(RequestMatcher { request -> CorsUtils.isPreFlightRequest(request) }).permitAll()

                // health check
                it.requestMatchers(HttpMethod.GET, "/").permitAll()

                // auth
                it.requestMatchers(HttpMethod.GET, "/auth").permitAll()
                it.requestMatchers(HttpMethod.POST, "/auth").permitAll()
                it.requestMatchers(HttpMethod.PATCH, "/auth").permitAll()
                it.requestMatchers(HttpMethod.DELETE, "/auth").authenticated()

                // homeBase
                it.requestMatchers(HttpMethod.POST, "/homebase").hasAnyRole(STUDENT, ADMIN)
                it.requestMatchers(HttpMethod.GET, "/homebase").authenticated()
                it.requestMatchers(HttpMethod.DELETE, "/homebase").hasRole(ADMIN)

                // reservation
                it.requestMatchers(HttpMethod.GET, "/reservation/{id}").authenticated()
                it.requestMatchers(HttpMethod.DELETE, "/reservation/{id}").hasAnyRole(STUDENT, ADMIN)
                it.requestMatchers(HttpMethod.PATCH, "/reservation/{id}").hasAnyRole(STUDENT, ADMIN)
                it.requestMatchers(HttpMethod.PATCH, "/reservation/{id}/check-status").hasAnyRole(TEACHER, ADMIN)
                it.requestMatchers(HttpMethod.PATCH, "/reservation/{id}/{user_id}").hasAnyRole(STUDENT, ADMIN)
                it.requestMatchers(HttpMethod.DELETE, "/reservation/{id}/exit").hasAnyRole(STUDENT, ADMIN)
                it.requestMatchers(HttpMethod.DELETE, "/reservation/kill-all").hasRole(ADMIN)

                // notice
                it.requestMatchers(HttpMethod.GET, "/notice").authenticated()
                it.requestMatchers(HttpMethod.GET, "/notice/{id}").authenticated()
                it.requestMatchers(HttpMethod.POST, "/notice").hasAnyRole(ADMIN, TEACHER)
                it.requestMatchers(HttpMethod.DELETE, "/notice/{id}").hasAnyRole(ADMIN, TEACHER)
                it.requestMatchers(HttpMethod.PATCH, "/notice/{id}").hasAnyRole(ADMIN, TEACHER)

                // user
                it.requestMatchers(HttpMethod.GET, "/user").authenticated()
                it.requestMatchers(HttpMethod.GET, "/user/my-page").authenticated()
                it.requestMatchers(HttpMethod.GET, "/user/students").authenticated()
                it.requestMatchers(HttpMethod.GET, "/user/my-role").authenticated()
                it.requestMatchers(HttpMethod.GET, "/user/search").hasAnyRole(ADMIN, TEACHER)
                it.requestMatchers(HttpMethod.GET, "/user/search-student").hasAnyRole(ADMIN, STUDENT)
                it.requestMatchers(HttpMethod.PATCH, "/user/{id}").hasAnyRole(ADMIN, TEACHER)
                it.requestMatchers(HttpMethod.PATCH, "/user/{id}/role").hasRole(ADMIN)
                it.anyRequest().denyAll()
            }
            .exceptionHandling {
                it.authenticationEntryPoint(CustomAuthenticationEntryPoint())
                it.accessDeniedHandler(CustomAccessDenied())
            }
            .addFilterBefore(JwtRequestFilter(jwtParserPort), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterAfter(ExceptionFilter(), JwtRequestFilter::class.java)

        return http.build()
    }

    @Bean
    protected fun passwordEncoder() = BCryptPasswordEncoder()
}