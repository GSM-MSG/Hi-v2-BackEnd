package team.msg.hiv2.domain.reservation.application.scheduler

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.constant.UseStatus

@Component
class ReservationScheduler(
    private val reservationService: ReservationService,
    private val userService: UserService
) {

    /**
     * 매일 08시 30분에 전체 예약 테이블 삭제와 유저 정지 여부 검증
     */
    @Scheduled(cron = "0 30 8 ? * MON-FRI", zone = "Asia/Seoul")
    fun resetAllReservation() = checkAndDelete()

    private fun checkAndDelete(){

        val reservations = reservationService.queryAllReservation()

        val users = userService.queryAllUserByReservationIn(reservations)

        val updatedUsers = users.map {
            val reservation = reservationService.queryReservationById(it.reservationId!!)
            when(reservation.checkStatus) {
                true -> it.copy(useStatus = UseStatus.AVAILABLE)
                false -> it.copy(useStatus = UseStatus.UNAVAILABLE )
            }
        }

        userService.saveAll(updatedUsers)

        reservationService.deleteAllReservationInBatch(reservations)
    }
}