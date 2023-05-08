package team.msg.hiv2.global.security.spi

import java.util.UUID

interface SecurityPort {
    fun queryCurrentUserId(): UUID
}