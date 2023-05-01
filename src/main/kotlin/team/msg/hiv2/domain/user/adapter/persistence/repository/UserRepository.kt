package team.msg.hiv2.domain.user.adapter.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.user.adapter.persistence.entity.UserJpaEntity
import java.util.*

interface UserRepository : CrudRepository<UserJpaEntity, UUID> {
}