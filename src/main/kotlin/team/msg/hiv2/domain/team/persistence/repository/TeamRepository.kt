package team.msg.hiv2.domain.team.persistence.repository

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.team.persistence.entity.TeamJpaEntity
import java.util.*

interface TeamRepository : CrudRepository<TeamJpaEntity, UUID> {
    fun findAllByIdIn(id: List<UUID>): List<TeamJpaEntity>
    fun findAllByUserIdsIn(userIds: List<UUID>): List<TeamJpaEntity>
    @Modifying
    @Query("DELETE FROM TeamJpaEntity t WHERE t IN :teams")
    fun deleteAllInBatch(teams: List<TeamJpaEntity>)
}