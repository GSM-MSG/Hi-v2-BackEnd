package team.msg.hiv2.domain.team.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.team.application.spi.TeamPort
import team.msg.hiv2.domain.team.domain.Team
import team.msg.hiv2.domain.team.persistence.mapper.TeamMapper
import team.msg.hiv2.domain.team.persistence.repository.TeamRepository
import java.util.*

@Component
class TeamPersistenceAdapter(
    private val teamRepository: TeamRepository,
    private val teamMapper: TeamMapper
) : TeamPort {

    override fun save(team: Team): Team =
        teamMapper.toDomain(teamRepository.save(teamMapper.toEntity(team)))!!

    override fun deleteAllTeamInBatch(teams: List<Team>) {
        teamRepository.deleteAllInBatch(teams.map { teamMapper.toEntity(it) })
    }

    override fun queryTeamByUserId(userId: UUID): Team? =
        teamMapper.toDomain(teamRepository.findByUserIdsIn(mutableListOf(userId)))

    override fun queryTeamById(id: UUID): Team? =
        teamMapper.toDomain(teamRepository.findByIdOrNull(id))

    override fun queryAllTeam(): List<Team> =
        teamRepository.findAll().map { teamMapper.toDomain(it)!! }
}