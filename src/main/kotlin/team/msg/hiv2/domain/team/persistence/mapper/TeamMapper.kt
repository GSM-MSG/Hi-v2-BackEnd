package team.msg.hiv2.domain.team.persistence.mapper

import org.springframework.stereotype.Component
import team.msg.hiv2.domain.team.domain.Team
import team.msg.hiv2.domain.team.persistence.entity.TeamJpaEntity
import team.msg.hiv2.global.mapper.GenericMapper

@Component
class TeamMapper : GenericMapper<Team, TeamJpaEntity> {
    override fun toDomain(entity: TeamJpaEntity?): Team? =
        entity?.let {
            Team(
                id = it.id,
                userIds = it.userIds
            )
        }

    override fun toEntity(domain: Team): TeamJpaEntity =
        domain.let {
            TeamJpaEntity(
                id = it.id,
                userIds = it.userIds
            )
        }
}