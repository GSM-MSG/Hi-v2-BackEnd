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

    @Around(
        "reserveHomeBaseUseCase_executePointCut()"
    )
    private fun loggingReservationInfo(joinPoint: ProceedingJoinPoint) {
        val ( floor, period, request ) = joinPoint.args
        log.info("homebase reserve execute() called with floor={}, period={}, request={}", floor,period, request)
    }

}