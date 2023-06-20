package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class ReserveHomeBaseUseCase(
    private val userValidator: UserValidator,
    private val userService: UserService,
    private val reservationService: ReservationService,
    private val homeBaseService: HomeBaseService
) {

    fun execute(floor: Int, period: Int, request: ReservationHomeBaseRequest){
        val currentUser = userService.queryCurrentUser()
        val homeBase = homeBaseService.queryHomeBaseByFloorAndPeriod(floor, period)
        val users = userService.queryAllUserById(request.users)

        userValidator.checkUsersUseStatus(users)

        val reservationId = reservationService.save(
            Reservation(
                id = UUID.randomUUID(),
                reason = request.reason,
                representativeId = currentUser.id,
                homeBaseId = homeBase.id,
                checkStatus = false
            )
        ).id

        userService.saveAll(users.map {
                it.copy(reservationId = reservationId, useStatus = UseStatus.UNAVAILABLE)
            }
        )
    }
}