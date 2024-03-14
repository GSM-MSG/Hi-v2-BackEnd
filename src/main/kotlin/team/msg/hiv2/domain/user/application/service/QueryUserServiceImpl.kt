package team.msg.hiv2.domain.user.application.service

import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.service.DomainService
import java.util.*

@DomainService
class QueryUserServiceImpl(
    private val queryUserPort: QueryUserPort
) : QueryUserService {

    override fun queryUserById(id: UUID): User =
        queryUserPort.queryUserById(id) ?: throw UserNotFoundException()

    override fun queryUserByEmail(email: String): User =
        queryUserPort.queryUserByEmail(email) ?: throw UserNotFoundException()

    override fun queryAllUserById(ids: List<UUID>): List<User> =
        queryUserPort.queryAllUserById(ids)

    override fun queryUserRoleByEmail(email: String, role: String): UserRole =
        queryUserPort.queryUserRoleByEmail(email, role)

    override fun existsUserByEmail(email: String): Boolean =
        queryUserPort.existsUserByEmail(email)

    override fun existsUsersByIds(ids: List<UUID>): Boolean =
        queryUserPort.existsUserByIds(ids)

    override fun queryCurrentUser(): User =
        queryUserPort.queryCurrentUser()

    override fun queryUserByNameContainingOrderByEmail(keyword: String): List<User> =
        queryUserPort.queryAllUserByNameContainingOrderByEmail(keyword)

    override fun queryAllUsersOrderByEmail(): List<User> =
        queryUserPort.queryAllUsersOrderByEmail()

    override fun queryAllUserByRoleContaining(role: UserRole): List<User> =
        queryUserPort.queryAllUserByRoleContaining(role)

    override fun queryAllUserByRoleOrderByEmail(role: UserRole): List<User> =
        queryUserPort.queryAllUserByRoleOrderByEmail(role)

    override fun queryAllUserByNameContainingAndRoleOrderByEmail(keyword: String, role: UserRole) =
        queryUserPort.queryAllUserByNameContainingAndRoleOrderByEmail(keyword, role)

}