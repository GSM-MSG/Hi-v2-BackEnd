package team.msg.hiv2.domain.user.application.service

import team.msg.hiv2.domain.reservation.domain.Reservation
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

    override fun queryAllUserByReservation(reservation: Reservation): List<User> =
        queryUserPort.queryAllUserByReservation(reservation)

    override fun queryUserByIdAndReservation(id: UUID, reservation: Reservation): User =
        queryUserPort.queryUserByIdAndReservation(id, reservation) ?: throw UserNotFoundException()

    override fun existsUserByEmail(email: String): Boolean =
        queryUserPort.existsUserByEmail(email)

    override fun queryCurrentUser(): User =
        queryUserPort.queryCurrentUser()

    override fun queryUserByNameContainingOrderByEmail(keyword: String): List<User> =
        queryUserPort.queryUserByNameContainingOrderByEmail(keyword)

    override fun queryAllUserByReservationIsNotNull(): List<User> =
        queryUserPort.queryAllUserByReservationIsNotNull()

    override fun queryAllUsersOrderByEmail(): List<User> =
        queryUserPort.queryAllUsersOrderByEmail()

    override fun queryAllUserByRoleContaining(role: UserRole): List<User> =
        queryUserPort.queryAllUserByRoleContaining(role)

    override fun queryAllUserByRoleOrderByEmail(role: UserRole): List<User> =
        queryUserPort.queryAllUserByRoleOrderByEmail(role)

    override fun queryAllUserByNameContainingAndRoleOrderByEmail(keyword: String, role: UserRole) =
        queryUserPort.queryAllUserByNameContainingAndRoleOrderByEmail(keyword, role)

    override fun queryAllUserByReservationIn(reservations: List<Reservation>): List<User> =
        queryUserPort.queryAllUserByReservationIn(reservations)

    override fun queryAllUsersByUserIds(userIds: List<UUID>): List<User> =
        queryUserPort.queryAllUserByUserIds(userIds)

}