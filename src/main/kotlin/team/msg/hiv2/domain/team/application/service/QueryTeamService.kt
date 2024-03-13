package team.msg.hiv2.domain.team.application.service

import team.msg.hiv2.domain.team.domain.Team
import java.util.UUID

interface QueryTeamService {
    fun queryTeamByUserId(userId: UUID): Team
    fun queryTeamById(id: UUID): Team
}