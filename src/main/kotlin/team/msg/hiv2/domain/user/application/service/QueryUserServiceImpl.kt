package team.msg.hiv2.domain.user.application.service

import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import java.util.*

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

    override fun queryUserByNameContaining(keyword: String): List<User> =
        queryUserPort.queryUserByNameContaining(keyword)

}