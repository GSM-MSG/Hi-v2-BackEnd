package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.service.QueryHomeBaseService
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import team.msg.hiv2.domain.reservation.application.service.CommandReservationService
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.application.service.CommandUserService
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class ReserveHomeBaseUseCase(
    private val userValidator: UserValidator,
    private val queryUserService: QueryUserService,
    private val commandUserService: CommandUserService,
    private val commandReservationService: CommandReservationService,
    private val queryHomeBaseService: QueryHomeBaseService
) {

    fun execute(floor: Int, period: Int, request: ReservationHomeBaseRequest){
        val currentUser = queryUserService.queryCurrentUser()
        val homeBase = queryHomeBaseService.queryHomeBaseByFloorAndPeriod(floor, period)
        val users = queryUserService.queryAllUserById(request.users)

        userValidator.checkUsersUseStatus(users)

        val reservationId = commandReservationService.save(
            Reservation(
                id = UUID.randomUUID(),
                reason = request.reason,
                representativeId = currentUser.id,
                homeBaseId = homeBase.id,
                checkStatus = false
            )
        ).id

        commandUserService.saveAll(users.map { it.copy(reservationId = reservationId, useStatus = UseStatus.UNAVAILABLE) })
    }
}