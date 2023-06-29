package team.msg.hiv2.domain.reservation.application.scheduler

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.application.usecase.DeleteAllReservationByPeriodUseCase
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.application.usecase.CheckAndRestrictReservationUserUseCase

@Component
class ReservationScheduler(
    private val deleteAllReservationByPeriodUseCase: DeleteAllReservationByPeriodUseCase,
    private val checkAndRestrictReservationUserUseCase: CheckAndRestrictReservationUserUseCase,
    private val reservationService: ReservationService,
    private val homeBaseService: HomeBaseService
) {

    /**
     * 매일 5시 30분에 8교시 예약 테이블 삭제와 유저 정지 여부 검증
     */
    @Scheduled(cron = "0 30 17 * * *", zone = "Asia/Seoul")
    fun resetAll8PeriodReservation() = checkAndDelete(8)


    /**
     * 매일 6시 30분에 8교시 예약 테이블 삭제와 유저 정지 여부 검증
     */
    @Scheduled(cron = "0 30 18 * * *", zone = "Asia/Seoul")
    fun resetAll9PeriodReservation() = checkAndDelete(9)

    /**
     * 매일 8시 20분에 8교시 예약 테이블 삭제와 유저 정지 여부 검증
     */
    @Scheduled(cron = "0 20 20 * * *", zone = "Asia/Seoul")
    fun resetAll10PeriodReservation() = checkAndDelete(10)

    /**
     * 매일 9시 20분에 8교시 예약 테이블 삭제와 유저 정지 여부 검증
     */
    @Scheduled(cron = "0 20 21 * * *", zone = "Asia/Seoul")
    fun resetAll11PeriodReservation() = checkAndDelete(11)

    private fun checkAndDelete(period: Int){

        val homeBases = homeBaseService.queryAllHomeBaseByPeriod(period)

        val reservations = reservationService.queryAllReservationByHomeBaseIn(homeBases)

        reservations.forEach {
            checkAndRestrictReservationUserUseCase.execute(it.id)
        }

        deleteAllReservationByPeriodUseCase.execute(period)
    }
}