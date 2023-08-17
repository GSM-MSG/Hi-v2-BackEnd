package team.msg.hiv2.domain.homebase.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@Aspect
class HomeBaseReservationInfoAspect {

    private val log by lazy { LoggerFactory.getLogger(this.javaClass.simpleName) }

    @Pointcut("execution(* team.msg.hiv2.domain.homebase.application.usecase.ReserveHomeBaseUseCase+.execute(..))")
    private fun reserveHomeBaseUseCase_executePointCut(){}

    @Pointcut("execution(* team.msg.hiv2.domain.reservation.application.usecase.UpdateReservationUseCase+.execute(..))")
    private fun updateReservationUseCase_executePointCut(){}

    @Around(
        "reserveHomeBaseUseCase_executePointCut() || updateReservationUseCase_executePointCut()",
    )
    private fun loggingReservationInfo(joinPoint: ProceedingJoinPoint) {
        val args = joinPoint.args
        log.info("homebase reserve execute() called with floor={}, period={}, request={}", args[0], args[1], args[2])
    }

}