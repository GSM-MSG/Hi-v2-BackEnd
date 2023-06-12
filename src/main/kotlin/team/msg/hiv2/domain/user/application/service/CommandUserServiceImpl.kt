package team.msg.hiv2.domain.user.application.service

import team.msg.hiv2.domain.user.application.spi.CommandUserPort
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class CommandUserServiceImpl(
    private val commandUserPort: CommandUserPort
) : CommandUserService {

    override fun save(user: User): User =
        commandUserPort.save(user)

    override fun saveAll(users: List<User>) =
        commandUserPort.saveAll(users)
}