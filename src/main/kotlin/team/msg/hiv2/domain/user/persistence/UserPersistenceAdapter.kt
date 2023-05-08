package team.msg.hiv2.domain.user.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
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
    private val securityPort: SecurityPort
) : UserPort {

    override fun save(user: User): User? =
        userMapper.toDomain(userRepository.save(userMapper.toEntity(user)))

    override fun queryUserById(id: UUID): User? {
        val user = userRepository.findByIdOrNull(id)
        return userMapper.toDomain(user)
    }

    override fun queryUserByEmail(email: String): User? {
        val user = userRepository.findByEmail(email)
        return userMapper.toDomain(user)
    }

    override fun queryUserRoleByEmail(email: String, role: String): UserRole {
        println(email)
        println(role)
        val user = queryUserByEmail(email) ?: return when(role){
            "ROLE_STUDENT" -> UserRole.ROLE_STUDENT
            "ROLE_TEACHER" -> UserRole.ROLE_TEACHER
            else -> throw InvalidRoleException()
        }
        return user.roles.firstOrNull() ?: throw UserNotFoundException()
    }

    override fun existsUserByEmail(email: String): Boolean =
        userRepository.existsByEmail(email)

    override fun queryCurrentUser(): User =
        userMapper.toDomain(userRepository.findByIdOrNull(securityPort.queryCurrentUserId()))
            .let { it ?: throw UserNotFoundException() }

}