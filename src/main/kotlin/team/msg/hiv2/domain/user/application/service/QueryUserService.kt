package team.msg.hiv2.domain.user.application.service

import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UserRole
import java.util.*

interface QueryUserService {

    fun queryUserById(id: UUID): User
    fun queryUserByEmail(email: String): User
    fun queryAllUserById(ids: List<UUID>): List<User>
    fun queryUserRoleByEmail(email: String, role: String): UserRole
    fun existsUserByEmail(email: String): Boolean
    fun queryCurrentUser(): User
    fun queryUserByNameContainingOrderByEmail(keyword: String): List<User>
    fun queryAllUsersOrderByEmail(): List<User>
    fun queryAllUserByRoleContaining(role: UserRole): List<User>
    fun queryAllUserByRoleOrderByEmail(role: UserRole): List<User>
    fun queryAllUserByNameContainingAndRoleOrderByEmail(keyword: String, role: UserRole): List<User>
    fun queryAllUsersByUserIds(userIds: List<UUID>): List<User>
}
