package team.msg.hiv2.global.security.handler

import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import team.msg.hiv2.global.error.exception.InvalidRoleException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAccessDenied : AccessDeniedHandler {

    private val log by lazy { LoggerFactory.getLogger(this.javaClass.simpleName) }

    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException,
    ) {
        log.info("============================== Access Denied ==============================")
        throw InvalidRoleException()
    }
}