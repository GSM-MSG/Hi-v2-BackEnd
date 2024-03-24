package team.msg.hiv2.domain.user.application.spi

import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UserRole
import java.util.*

interface QueryUserPort {

    fun queryUserById(id: UUID): User?
    fun queryUserByEmail(email: String): User?
    fun queryAllUserById(ids: List<UUID>): List<User>
    fun queryUserRoleByEmail(email: String, role: String): UserRole
    fun existsUserByEmail(email: String): Boolean
    fun existsUserByIds(ids: List<UUID>): Boolean
    fun queryCurrentUser(): User
    fun queryAllUserByNameContainingOrderByEmail(keyword: String): List<User>
    fun queryAllUsersOrderByEmail(): List<User>
    fun queryAllUserByRoleContaining(role: UserRole): List<User>
    fun queryAllUserByRoleOrderByEmail(role: UserRole): List<User>
    fun queryAllUserByNameContainingAndRoleInOrderByEmail(keyword: String, role: List<UserRole>): List<User>
}