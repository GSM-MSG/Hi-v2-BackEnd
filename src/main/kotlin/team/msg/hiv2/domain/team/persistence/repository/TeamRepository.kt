package team.msg.hiv2.domain.team.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.msg.hiv2.domain.team.persistence.entity.TeamJpaEntity
import java.util.*

interface TeamRepository : JpaRepository<TeamJpaEntity, UUID> {

    fun findAllByIdIn(id: List<UUID>): List<TeamJpaEntity>
    fun findAllByUserIdsIn(userIds: List<UUID>): List<TeamJpaEntity>
}