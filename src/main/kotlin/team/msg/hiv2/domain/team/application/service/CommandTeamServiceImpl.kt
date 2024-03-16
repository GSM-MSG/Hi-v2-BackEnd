package team.msg.hiv2.domain.team.application.service

import team.msg.hiv2.domain.team.application.spi.CommandTeamPort
import team.msg.hiv2.domain.team.domain.Team
import team.msg.hiv2.global.annotation.service.DomainService
import java.util.*

@DomainService
class CommandTeamServiceImpl(
    private val commandTeamPort: CommandTeamPort
) : CommandTeamService {

    override fun save(team: Team) =
        commandTeamPort.save(team)

    override fun deleteAll(teams: List<Team>) {
        commandTeamPort.deleteAll(teams)
    }

    override fun deleteTeamById(id: UUID) {
        commandTeamPort.deleteTeamById(id)
    }

    override fun deleteAll() {
        commandTeamPort.deleteAll()
    }
}