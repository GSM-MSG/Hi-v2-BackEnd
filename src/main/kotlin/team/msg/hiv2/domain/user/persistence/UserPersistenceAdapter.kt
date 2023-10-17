package team.msg.hiv2.domain.user.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.persistence.mapper.ReservationMapper
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.domain.user.persistence.mapper.UserMapper
import team.msg.hiv2.domain.user.persistence.repository.UserRepository
import team.msg.hiv2.global.error.exception.InvalidRoleException
import team.msg.hiv2.global.security.spi.SecurityPort
import java.util.*

@Component
class UserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val securityPort: SecurityPort,
    private val reservationMapper: ReservationMapper,
) : UserPort {

    override fun save(user: User): User =
        userMapper.toDomain(userRepository.save(userMapper.toEntity(user)))!!

    override fun saveAll(users: List<User>) {
        userRepository.saveAll(users.map { userMapper.toEntity(it) })
    }

    override fun queryUserById(id: UUID): User? =
        userMapper.toDomain(userRepository.findByIdOrNull(id))

    override fun queryUserByEmail(email: String): User? {
        val user = userRepository.findByEmail(email)
        return userMapper.toDomain(user)
    }

    override fun queryAllUserById(ids: List<UUID>): List<User> =
        userRepository.findAllById(ids).map { userMapper.toDomain(it)!! }

    override fun queryUserRoleByEmail(email: String, role: String): UserRole {
        val user = queryUserByEmail(email) ?: return when (role) {
            "ROLE_STUDENT" -> UserRole.ROLE_STUDENT
            "ROLE_TEACHER" -> UserRole.ROLE_TEACHER
            else -> throw InvalidRoleException()
        }
        return user.roles.firstOrNull() ?: throw UserNotFoundException()
    }

    override fun queryAllUserByReservation(reservation: Reservation): List<User> =
        userRepository.findAllByReservation(reservationMapper.toEntity(reservation))
            .map { userMapper.toDomain(it)!! }

    override fun queryUserByIdAndReservation(id: UUID, reservation: Reservation): User? =
        userMapper.toDomain(userRepository.findByIdAndReservation(id, reservationMapper.toEntity(reservation)))

    override fun existsUserByEmail(email: String): Boolean =
        userRepository.existsByEmail(email)

    override fun queryCurrentUser(): User =
        userMapper.toDomain(userRepository.findByIdOrNull(securityPort.queryCurrentUserId()))
            .let { it ?: throw UserNotFoundException() }

    override fun queryUserByNameContainingOrderByEmail(keyword: String): List<User> =
        userRepository.findAllByNameContainingOrderByEmail(keyword).map { userMapper.toDomain(it)!! }

    override fun queryAllUserByReservationIsNotNull(): List<User> =
        userRepository.findAllByReservationIsNotNull().map { userMapper.toDomain(it)!! }

    override fun queryAllUsers(): List<User> =
        userRepository.findAll().map { userMapper.toDomain(it)!! }

    override fun queryAllUserByRolesContaining(role: UserRole): List<User> =
        userRepository.findAllByRolesContaining(role).map { userMapper.toDomain(it)!! }

    override fun queryAllUserByRolesContainingOrderByEmail(role: UserRole): List<User> =
        userRepository.findAllByRolesContainingOrderByEmail(role).map { userMapper.toDomain(it)!! }

    override fun queryAllUserByNameContainingAndRolesContainingOrderByEmail(keyword: String,role: UserRole): List<User>  =
        userRepository.findAllByNameContainingAndRolesContainingOrderByEmail(keyword, role).map { userMapper.toDomain(it)!! }

    override fun queryAllUserByReservationIn(reservations: List<Reservation>): List<User> =
        userRepository.findAllByReservationIn(reservations.map { reservationMapper.toEntity(it) })
            .map { userMapper.toDomain(it)!! }

}