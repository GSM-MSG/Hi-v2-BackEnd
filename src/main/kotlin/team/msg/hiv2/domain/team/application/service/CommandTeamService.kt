package team.msg.hiv2.domain.team.application.service

import team.msg.hiv2.domain.team.domain.Team

interface CommandTeamService {

    fun save(team: Team): Team
    fun deleteAllTeamInBatch(teams: List<Team>)
}