package team.msg.hiv2.aspect

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.exception.AlreadyReservedAtSamePeriodException
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import java.util.*

@Aspect
@Component
class ReservationControlAspect(
    private val userService: UserService,
    private val reservationService: ReservationService,
    private val userValidator: UserValidator,
    private val homeBaseService: HomeBaseService,
    private val teamService: TeamService
) {

    @Pointcut("execution(* team.msg.hiv2.domain.homebase.application.usecase.ReserveHomeBaseUseCase.execute(..)) " +
            "&& args(floor, period, homeBaseNumber, request) && within(team.msg.hiv2.domain.homebase.application.usecase.ReserveHomeBaseUseCase)")
    private fun reserveHomeBaseUseCasePointcut(floor: Int, period: Int, homeBaseNumber: Int, request: ReservationHomeBaseRequest) {}

    @Pointcut("execution(* team.msg.hiv2.domain.reservation.application.usecase.UpdateReservationUseCase.execute(..)) " +
            "&& args(reservationId, request) && within(team.msg.hiv2.domain.reservation.application.usecase.UpdateReservationUseCase)")
    private fun updateReservationUseCasePointcut(reservationId: UUID, request: UpdateReservationRequest) {}

    @Pointcut("execution(* team.msg.hiv2.domain.reservation.application.usecase.ExitReservationUseCase.execute(..))" +
            " && args(reservationId) && within(team.msg.hiv2.domain.reservation.application.usecase.ExitReservationUseCase)")
    private fun exitReservationUseCasePointcut(reservationId: UUID){}

    @Before("reserveHomeBaseUseCasePointcut(floor, period, homeBaseNumber, request)")
    private fun checkReserveHomeBase(floor: Int, period: Int, homeBaseNumber: Int, request: ReservationHomeBaseRequest) {
        val currentUser = userService.queryCurrentUser()

        userValidator.checkUserUseStatus(currentUser)
        userValidator.checkUsersUseStatus(userService.queryAllUserById(request.users))

        val homeBases = homeBaseService.queryHomeBaseByPeriod(period)
        val teamIds = reservationService.queryAllReservationByHomeBaseIn(homeBases).map { reservation -> reservation.teamId }
        val userIds = teamService.queryAllTeamByIdIn(teamIds).flatMap { team -> team.userIds }

        if (userIds.any { it in request.users })
            throw AlreadyReservedAtSamePeriodException()
    }

    @Before("updateReservationUseCasePointcut(reservationId, request)")
    private fun checkUpdateReservation(reservationId: UUID, request: UpdateReservationRequest) {
        val currentUser = userService.queryCurrentUser()

        userValidator.checkUserUseStatus(currentUser)
        userValidator.checkUsersUseStatus(userService.queryAllUserById(request.users))
    }

    @Before("exitReservationUseCasePointcut(reservationId)")
    private fun checkAuthorizationExitReservation(reservationId: UUID) {
        val currentUser = userService.queryCurrentUser()
        val reservation = reservationService.queryReservationById(reservationId)

        userValidator.checkUserAndReservation(currentUser, reservation)
    }

}
