package team.msg.hiv2.global.security.handler

import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import team.msg.hiv2.global.error.exception.ForbiddenException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val log by lazy { LoggerFactory.getLogger(this.javaClass.simpleName) }

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?,
    ) {
        log.info("============================== AuthenticationEntryPoint ==============================")
        throw ForbiddenException()
    }
}