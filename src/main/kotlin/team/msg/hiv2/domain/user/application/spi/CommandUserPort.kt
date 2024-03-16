package team.msg.hiv2.domain.user.application.spi

import team.msg.hiv2.domain.user.domain.User

interface CommandUserPort {

    fun save(user: User) : User
    fun saveAll(users: List<User>)
}