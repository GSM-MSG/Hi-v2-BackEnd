package team.msg.hiv2.domain.team.application.spi

import team.msg.hiv2.domain.team.domain.Team

interface CommandTeamPort {
    fun save(team: Team): Team
}