package team.msg.hiv2.domain.user.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.persistence.entity.UserJpaEntity
import java.util.*

interface UserRepository : CrudRepository<UserJpaEntity, UUID> {

    fun findByEmail(email: String): UserJpaEntity?
    fun existsByEmail(email: String): Boolean
    fun findAllByNameContainingOrderByGradeAscClassNumAscNumberAsc(keyword: String): List<UserJpaEntity>
    fun findAllByRoleContaining(role: UserRole): List<UserJpaEntity>
    fun findAllByRoleOrderByGradeAscClassNumAscNumberAsc(role: UserRole): List<UserJpaEntity>
    fun findAllByNameContainingAndRoleInOrderByGradeAscClassNumAscNumberAsc(keyword: String, role: List<UserRole>): List<UserJpaEntity>
}