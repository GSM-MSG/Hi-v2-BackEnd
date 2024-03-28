package team.msg.hiv2.domain.reservation.application.scheduler

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.reservation.application.usecase.CheckAndRestrictReservationUserUseCase

@Component
class ReservationScheduler(
    private val checkAndRestrictReservationUserUseCase: CheckAndRestrictReservationUserUseCase
) {

    /**
     * 매일 0시 0분에 전체 예약 테이블 삭제와 유저 정지 여부 검증
     */
    @Scheduled(cron = "0 0 0 ? * MON-FRI", zone = "Asia/Seoul")
    fun resetAllReservation() = checkAndRestrictReservationUserUseCase.execute()

}