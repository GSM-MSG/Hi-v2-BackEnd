package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class UpdateReservationUseCase(
    private val reservationService: ReservationService,
    private val userService: UserService
) {

    fun execute(reservationId: UUID, request: UpdateReservationRequest){
        val reservation = reservationService.queryReservationById(reservationId)

        val prevUsers = userService.queryAllUserByReservation(reservation)

        userService.saveAll(prevUsers.map { it.copy(reservationId = null , useStatus = UseStatus.AVAILABLE) })

        val users = userService.queryAllUserById(request.users)

        reservationService.save(reservation.copy(reason = request.reason))
        userService.saveAll(users.map { it.copy(reservationId = reservationId) })
    }
}