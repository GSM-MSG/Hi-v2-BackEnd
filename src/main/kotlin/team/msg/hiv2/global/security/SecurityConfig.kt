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
            .antMatchers(HttpMethod.GET, "/auth").permitAll()
            .antMatchers(HttpMethod.POST, "/auth").permitAll()
            .antMatchers(HttpMethod.PATCH, "/auth").permitAll()
            .antMatchers(HttpMethod.DELETE, "/auth").authenticated()

            // homeBase
            .antMatchers(HttpMethod.POST, "/homebase").hasAnyRole(STUDENT, ADMIN)
            .antMatchers(HttpMethod.GET, "/homebase").authenticated()
            .antMatchers(HttpMethod.DELETE, "/homebase").hasRole(ADMIN)

            // reservation
            .antMatchers(HttpMethod.GET, "/reservation/{id}").authenticated()
            .antMatchers(HttpMethod.DELETE, "/reservation/{id}").hasAnyRole(STUDENT, ADMIN)
            .antMatchers(HttpMethod.PATCH, "/reservation/{id}").hasAnyRole(STUDENT, ADMIN)
            .antMatchers(HttpMethod.PATCH, "/reservation/{id}/{user_id}").hasAnyRole(STUDENT, ADMIN)
            .antMatchers(HttpMethod.DELETE, "/reservation/{id}/exit").hasAnyRole(STUDENT, ADMIN)
            .antMatchers(HttpMethod.PATCH, "/reservation/{id}/check-status").hasAnyRole(TEACHER, ADMIN)
            .antMatchers(HttpMethod.DELETE, "/reservation/kill-all").hasRole(ADMIN)

            // notice
            .antMatchers(HttpMethod.GET, "/notice").authenticated()
            .antMatchers(HttpMethod.GET, "/notice/{id}").authenticated()
            .antMatchers(HttpMethod.POST, "/notice").hasAnyRole(ADMIN, TEACHER)
            .antMatchers(HttpMethod.DELETE, "/notice/{id}").hasAnyRole(ADMIN, TEACHER)
            .antMatchers(HttpMethod.PATCH, "/notice/{id}").hasAnyRole(ADMIN, TEACHER)

            // user
            .antMatchers(HttpMethod.GET, "/user").authenticated()
            .antMatchers(HttpMethod.GET, "/user/my-page").authenticated()
            .antMatchers(HttpMethod.GET, "/user/students").authenticated()
            .antMatchers(HttpMethod.GET, "/user/search").authenticated()
            .antMatchers(HttpMethod.PATCH, "/user/{id}").hasAnyRole(ADMIN, TEACHER)
            .antMatchers(HttpMethod.PATCH, "/user/{id}/role").hasRole(ADMIN)

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