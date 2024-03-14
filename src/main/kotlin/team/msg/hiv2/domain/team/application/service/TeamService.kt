package team.msg.hiv2.domain.team.application.service

import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class TeamService(
    commandTeamService: CommandTeamService,
    queryTeamService: QueryTeamService
) : CommandTeamService by commandTeamService,
    QueryTeamService by queryTeamService