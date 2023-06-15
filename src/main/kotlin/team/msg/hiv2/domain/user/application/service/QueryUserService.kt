package team.msg.hiv2.domain.user.application.service

import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UserRole
import java.util.*

interface QueryUserService {

    fun queryUserById(id: UUID): User
    fun queryUserByEmail(email: String): User
    fun queryAllUserById(ids: List<UUID>): List<User>
    fun queryUserRoleByEmail(email: String, role: String): UserRole
    fun queryAllUserByReservation(reservation: Reservation): List<User>
    fun queryUserByIdAndReservation(id: UUID, reservation: Reservation): User
    fun existsUserByEmail(email: String): Boolean
    fun queryCurrentUser(): User
    fun queryUserByNameContaining(keyword: String): List<User>
    fun queryAllUserByUserRole(role: UserRole): List<User>
}