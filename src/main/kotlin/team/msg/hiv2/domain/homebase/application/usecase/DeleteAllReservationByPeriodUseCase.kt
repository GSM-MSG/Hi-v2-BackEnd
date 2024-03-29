package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class DeleteAllReservationByPeriodUseCase(
    private val homeBaseService: HomeBaseService,
    private val reservationService: ReservationService
) {

    fun execute(period: Int) {

        val homeBases = homeBaseService.queryAllHomeBaseByPeriod(period)

        val reservations = reservationService.queryAllReservationByHomeBaseIn(homeBases)

        reservationService.deleteAllReservationInBatch(reservations)
    }
}