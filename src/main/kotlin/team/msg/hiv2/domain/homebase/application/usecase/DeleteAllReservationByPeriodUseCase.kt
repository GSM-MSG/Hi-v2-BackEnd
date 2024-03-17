package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.team.persistence.mapper.TeamMapper
import team.msg.hiv2.domain.team.persistence.repository.TeamRepository
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class DeleteAllReservationByPeriodUseCase(
    private val homeBaseService: HomeBaseService,
    private val reservationService: ReservationService,
    private val teamService: TeamService,
    private val teamRepository: TeamRepository,
    private val teamMapper: TeamMapper
) {

    fun execute(period: Int) {

        val homeBases = homeBaseService.queryAllHomeBaseByPeriod(period)

        val reservations = reservationService.queryAllReservationByHomeBaseIn(homeBases)

        val teams = teamService.queryAllTeamByIdIn(reservations.map { it.teamId })

        reservationService.deleteAllReservationInBatch(reservations)
        teamRepository.deleteAll(teams.map { teamMapper.toEntity(it) })
    }
}