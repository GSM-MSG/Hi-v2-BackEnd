package team.msg.hiv2.domain.user.application.service

import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class CommandUserServiceImpl(
    private val userPort: UserPort
) : CommandUserService {

    override fun save(user: User): User =
        userPort.save(user)

    override fun saveAll(users: List<User>) =
        userPort.saveAll(users)

    override fun createUser(user: User, isExist: Boolean): User =
        if(isExist){
            userPort.queryUserByEmail(user.email) ?: throw UserNotFoundException()
        } else {
            userPort.save(user)
        }
}