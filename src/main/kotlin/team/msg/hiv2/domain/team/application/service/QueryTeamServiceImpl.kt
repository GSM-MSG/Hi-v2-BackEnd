package team.msg.hiv2.domain.team.application.service

import team.msg.hiv2.domain.team.application.spi.QueryTeamPort
import team.msg.hiv2.domain.team.domain.Team
import team.msg.hiv2.domain.team.exception.TeamNotFoundException
import java.util.*

class QueryTeamServiceImpl(
    private val queryTeamPort: QueryTeamPort
) : QueryTeamService {
    override fun queryTeamByUserId(userId: UUID): Team =
        queryTeamPort.queryTeamByUserId(userId)
            ?: throw TeamNotFoundException()

    override fun queryTeamById(id: UUID): Team =
        queryTeamPort.queryTeamById(id)
            ?: throw TeamNotFoundException()

    override fun queryAllTeam(): List<Team> =
        queryTeamPort.queryAllTeam()
}