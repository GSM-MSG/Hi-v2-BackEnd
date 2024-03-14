package team.msg.hiv2.domain.homebase.application.usecase

import org.springframework.data.repository.findByIdOrNull
import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.exception.ReservationNotFoundException
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.team.application.spi.TeamPort
import team.msg.hiv2.domain.team.persistence.repository.TeamRepository
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class DeleteAllReservationByPeriodUseCase(
    private val homeBaseService: HomeBaseService,
    private val reservationService: ReservationService,
    private val teamService: TeamService
) {

    fun execute(period: Int){
        val homeBases = homeBaseService.queryAllHomeBaseByPeriod(period)

        val reservations = reservationService.queryAllReservationByHomeBaseIn(homeBases)

        val teams = teamService.queryAllTeamByUserIdsIn(reservations.map { it.teamId })

        teamService.deleteAll(teams)
        reservationService.deleteAllReservationInBatch(reservations)
    }
}