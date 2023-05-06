package team.msg.hiv2.domain.user.application.spi

import team.msg.hiv2.domain.user.domain.User
import java.util.UUID

interface QueryUserPort {
    fun queryUserById(id: UUID): User
    fun queryUserByEmail(email: String): User
}