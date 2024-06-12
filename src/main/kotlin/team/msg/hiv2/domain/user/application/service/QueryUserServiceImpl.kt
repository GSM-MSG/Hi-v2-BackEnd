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

    override fun queryCurrentUser(): User =
        queryUserPort.queryCurrentUser()

    override fun queryUserByNameContainingOrderBySchoolNumber(keyword: String): List<User> =
        queryUserPort.queryAllUserByNameContainingOrderBySchoolNumber(keyword)

    override fun queryAllUserByRoleContaining(role: UserRole): List<User> =
        queryUserPort.queryAllUserByRoleContaining(role)

    override fun queryAllUserByRoleOrderBySchoolNumber(role: UserRole): List<User> =
        queryUserPort.queryAllUserByRoleOrderBySchoolNumber(role)

    override fun queryAllUserByNameContainingAndRoleInOrderBySchoolNumber(keyword: String, role: List<UserRole>) =
        queryUserPort.queryAllUserByNameContainingAndRoleInOrderBySchoolNumber(keyword, role)

}