package team.msg.hiv2.domain.user.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.domain.user.persistence.mapper.UserMapper
import team.msg.hiv2.domain.user.persistence.repository.UserRepository
import java.util.*

@Component
class UserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UserPort {

    override fun save(user: User) {
        userRepository.save(userMapper.toEntity(user))
    }

    override fun queryUserById(id: UUID): User {
        val user = userRepository.findByIdOrNull(id) ?: throw UserNotFoundException()
        return userMapper.toDomain(user)!!
    }

    override fun queryUserByEmail(email: String): User {
        val user = userRepository.findByEmail(email) ?: throw UserNotFoundException()
        return userMapper.toDomain(user)!!
    }
}