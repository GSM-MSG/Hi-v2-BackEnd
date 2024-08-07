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
    fun queryCurrentUser(): User
    fun queryAllUserByNameContainingOrderBySchoolNumber(keyword: String): List<User>
    fun queryAllUserByRoleOrderBySchoolNumber(role: UserRole): List<User>
    fun searchStudent(keyword: String): List<User>
}