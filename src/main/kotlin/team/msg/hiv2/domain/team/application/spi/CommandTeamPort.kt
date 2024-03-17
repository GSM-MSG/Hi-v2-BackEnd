package team.msg.hiv2.domain.team.application.spi

import team.msg.hiv2.domain.team.domain.Team
import java.util.UUID

interface CommandTeamPort {

    fun save(team: Team): Team
    fun deleteAllInBatch(teams: List<Team>)
    fun deleteTeamById(id: UUID)
    fun deleteAllInBatch()
}