package team.msg.hiv2.domain.team.application.service

import team.msg.hiv2.domain.team.application.spi.QueryTeamPort
import team.msg.hiv2.domain.team.domain.Team
import team.msg.hiv2.domain.team.exception.TeamNotFoundException
import team.msg.hiv2.global.annotation.service.DomainService
import java.util.*

@DomainService
class QueryTeamServiceImpl(
    private val queryTeamPort: QueryTeamPort
) : QueryTeamService {
    override fun queryAllTeamByUserId(userId: UUID): List<Team> =
        queryTeamPort.queryTeamByUserId(userId)

    override fun queryTeamById(id: UUID): Team =
        queryTeamPort.queryTeamById(id)
            ?: throw TeamNotFoundException()

    override fun queryAllTeam(): List<Team> =
        queryTeamPort.queryAllTeam()
}