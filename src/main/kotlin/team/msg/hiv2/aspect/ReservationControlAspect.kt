package team.msg.hiv2.aspect

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.user.application.service.UserService
import org.aspectj.lang.JoinPoint
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.user.application.validator.UserValidator
import java.util.*

@Aspect
@Component
class ReservationControlAspect(
    private val userService: UserService,
    private val reservationService: ReservationService,
    private val userValidator: UserValidator
) {

    @Pointcut("execution(* team.msg.hiv2.domain.reservation.application.usecase.UpdateReservationUseCase.execute(..)) && args(floor, period, request) && within(team.msg.hiv2.domain.homebase.application.usecase.ReserveHomeBaseUseCase)")
    fun reserveHomeBaseUseCasePointcut(floor: Int, period: Int, request: ReservationHomeBaseRequest) {}

    @Pointcut("execution(* team.msg.hiv2.domain.reservation.application.usecase.UpdateReservationUseCase.execute(..)) && args(reservationId, request) && within(team.msg.hiv2.domain.reservation.application.usecase.UpdateReservationUseCase)")
    fun updateReservationUseCasePointcut(reservationId: UUID, request: UpdateReservationRequest) {}

    @Before("reserveHomeBaseUseCasePointcut(floor, period, request)")
    fun checkAuthorizationReserveHomeBase(joinPoint: JoinPoint, floor: Int, period: Int, request: ReservationHomeBaseRequest) {
        val currentUser = userService.queryCurrentUser()

        userValidator.checkUserUseStatus(currentUser)
        userValidator.checkUsersUseStatus(userService.queryAllUserById(request.users))
    }

    @Before("updateReservationUseCasePointcut(reservationId, request)")
    fun checkAuthorizationUpdateReservation(joinPoint: JoinPoint, reservationId: UUID, request: UpdateReservationRequest) {
        val currentUser = userService.queryCurrentUser()
        val reservation = reservationService.queryReservationById(reservationId)

        userValidator.checkRepresentative(currentUser, reservation)
        userValidator.checkUsersUseStatus(userService.queryAllUserById(request.users))
    }

}
