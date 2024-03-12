package team.msg.hiv2.domain.team.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.team.persistence.entity.TeamJpaEntity
import java.util.UUID

interface TeamJpaRepository : CrudRepository<TeamJpaEntity, UUID> {
}