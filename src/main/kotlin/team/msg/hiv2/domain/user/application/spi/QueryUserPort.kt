package team.msg.hiv2.domain.user.application.spi

import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UserRole
import java.util.UUID

interface QueryUserPort {
    fun queryUserById(id: UUID): User?
    fun queryUserByEmail(email: String): User?
    fun queryUserRoleByEmail(email: String, role: String): UserRole
    fun existsUserByEmail(email: String): Boolean
}