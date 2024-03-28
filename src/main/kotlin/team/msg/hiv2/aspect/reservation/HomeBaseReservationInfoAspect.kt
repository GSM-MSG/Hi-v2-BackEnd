package team.msg.hiv2.aspect.reservation

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
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

    @Pointcut("execution(* team.msg.hiv2.domain.homebase.application.usecase.ReserveHomeBaseUseCase.execute(..)) " +
            "&& args(floor, period, request) && within(team.msg.hiv2.domain.homebase.application.usecase.ReserveHomeBaseUseCase)")
    private fun reserveHomeBaseUseCasePointcut(floor: Int, period: Int, request: ReservationHomeBaseRequest) {}

    @Before("reserveHomeBaseUseCasePointcut(floor, period, request)")
    private fun loggingReservationInfo(floor: Int, period: Int, request: ReservationHomeBaseRequest) {
        val currentUser = userService.queryCurrentUser()
        log.info("reserve homebase by = {}, floor = {}, period = {}, users = {}",
            currentUser.id, floor, period, request.users)
    }

}