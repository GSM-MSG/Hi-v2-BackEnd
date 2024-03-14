package team.msg.hiv2.domain.team.application.service

import team.msg.hiv2.domain.team.domain.Team
import java.util.UUID

interface QueryTeamService {
    fun queryAllTeamByUserId(userId: UUID): List<Team>
    fun queryTeamById(id: UUID): Team
    fun queryAllTeam(): List<Team>
}