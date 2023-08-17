package team.msg.hiv2.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import team.msg.hiv2.domain.user.application.service.UserService

@Component
@Aspect
class HomeBaseReservationInfoAspect(
    private val userService: UserService
) {

    private val log by lazy { LoggerFactory.getLogger(this.javaClass.simpleName) }

    @Pointcut("execution(* team.msg.hiv2.domain.reservation.application.usecase.UpdateReservationUseCase.execute(..)) && args(floor, period, request) && within(team.msg.hiv2.domain.homebase.application.usecase.ReserveHomeBaseUseCase)")
    fun reserveHomeBaseUseCasePointcut(floor: Int, period: Int, request: ReservationHomeBaseRequest) {}

    @Around(
        "reserveHomeBaseUseCase_executePointCut(floor, period, request)"
    )
    private fun loggingReservationInfo(joinPoint: JoinPoint, floor: Int, period: Int, request: ReservationHomeBaseRequest) {
        val currentUser = userService.queryCurrentUser()
        log.info("reserve homebase by = {}, floor = {}, period = {}, users = {}",
            currentUser.id, floor, period, request.users)
    }

}