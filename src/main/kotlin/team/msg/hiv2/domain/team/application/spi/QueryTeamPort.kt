package team.msg.hiv2.domain.team.application.spi

import team.msg.hiv2.domain.team.domain.Team
import java.util.UUID

interface QueryTeamPort {
    fun queryTeamByUserId(userId: UUID): List<Team>
    fun queryTeamById(id: UUID): Team?
    fun queryAllTeam(): List<Team>
}