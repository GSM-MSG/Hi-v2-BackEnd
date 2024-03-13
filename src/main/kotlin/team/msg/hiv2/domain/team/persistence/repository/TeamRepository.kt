package team.msg.hiv2.domain.team.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.team.persistence.entity.TeamJpaEntity
import java.util.*

interface TeamRepository : CrudRepository<TeamJpaEntity, UUID> {
    fun findByUserIdsIn(userId: MutableList<UUID>): TeamJpaEntity?
}