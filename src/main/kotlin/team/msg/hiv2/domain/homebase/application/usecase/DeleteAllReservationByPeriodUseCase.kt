package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class DeleteAllReservationByPeriodUseCase(
    private val homeBaseService: HomeBaseService,
    private val reservationService: ReservationService,
    private val userService: UserService
) {

    fun execute(period: Int){
        val homeBases = homeBaseService.queryAllHomeBaseByPeriod(period)

        val reservations = reservationService.queryAllReservationByHomeBaseIn(homeBases)

        val users = userService.queryAllUserByReservationIn(reservations)

        userService.saveAll(users.map { it.copy(useStatus = UseStatus.AVAILABLE, reservationId = null) })
        reservationService.deleteAllReservationInBatch(reservations)
    }
}