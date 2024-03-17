package team.msg.hiv2.domain.user.application.service

import team.msg.hiv2.domain.user.domain.User

interface CommandUserService {

    fun save(user: User) : User
    fun saveAll(users: List<User>)
    fun createUser(user: User, isExist: Boolean): User
}