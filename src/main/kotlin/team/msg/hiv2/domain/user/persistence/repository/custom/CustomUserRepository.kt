package team.msg.hiv2.domain.user.persistence.repository.custom

import team.msg.hiv2.domain.user.persistence.entity.UserJpaEntity

interface CustomUserRepository {
    fun searchStudent(keyword: String): List<UserJpaEntity>
}