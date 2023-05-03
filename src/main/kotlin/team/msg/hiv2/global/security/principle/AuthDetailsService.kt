package team.msg.hiv2.global.security.principle

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.domain.user.persistence.repository.UserRepository

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class AuthDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService{

    override fun loadUserByUsername(username: String): UserDetails =
        AuthDetails(
            userRepository.findByEmail(username)
                .let { it ?: throw UserNotFoundException() }
        )
}