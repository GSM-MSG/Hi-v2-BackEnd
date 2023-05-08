package team.msg.hiv2.global.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import team.msg.hiv2.global.security.spi.SecurityPort
import java.util.*

@Component
class SecurityAdapter : SecurityPort {
    override fun queryCurrentUserId(): UUID =
         UUID.fromString(SecurityContextHolder.getContext().authentication.name)
}