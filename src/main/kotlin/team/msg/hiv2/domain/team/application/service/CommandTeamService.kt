package team.msg.hiv2.domain.team.application.service

import team.msg.hiv2.domain.team.domain.Team
import java.util.UUID

interface CommandTeamService {

    fun save(team: Team): Team
    fun deleteAll()
    fun deleteAllInBatch(teams: List<Team>)
    fun deleteTeamById(id: UUID)
    fun deleteAllInBatch()
}