package team.msg.hiv2.domain.team.persistence

import org.springframework.stereotype.Component
import team.msg.hiv2.domain.team.application.spi.TeamPort
import team.msg.hiv2.domain.team.domain.Team
import team.msg.hiv2.domain.team.persistence.mapper.TeamMapper
import team.msg.hiv2.domain.team.persistence.repository.TeamRepository

@Component
class TeamPersistenceAdapter(
    private val teamRepository: TeamRepository,
    private val teamMapper: TeamMapper

) : TeamPort {
    override fun save(team: Team): Team =
        teamMapper.toDomain(teamRepository.save(teamMapper.toEntity(team)))!!
}