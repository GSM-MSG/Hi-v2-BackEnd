package team.msg.hiv2.domain.user.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.persistence.entity.UserJpaEntity
import team.msg.hiv2.domain.user.persistence.repository.custom.CustomUserRepository
import java.util.*

interface UserRepository : CrudRepository<UserJpaEntity, UUID>, CustomUserRepository {

    fun findByEmail(email: String): UserJpaEntity?
    fun existsByEmail(email: String): Boolean
    fun findAllByNameContainingOrderBySchoolNumberAsc(keyword: String): List<UserJpaEntity>
    fun findAllByRoleOrderBySchoolNumberAsc(role: UserRole): List<UserJpaEntity>
}