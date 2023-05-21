package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.spi.ReservationPort
import team.msg.hiv2.domain.reservation.exception.ReservationNotFoundException
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class UpdateReservationUseCase(
    private val reservationPort: ReservationPort,
    private val userPort: UserPort,
    private val userValidator: UserValidator
) {

    fun execute(reservationId: UUID, request: UpdateReservationRequest){
        val reservation = reservationPort.queryReservationById(reservationId) ?: throw ReservationNotFoundException()
        val currentUser = userPort.queryCurrentUser()
        userValidator.checkRepresentative(currentUser, reservation)

        val prevUsers = userPort.queryAllUserByReservation(reservation)
        userPort.saveAll(prevUsers.map { it.copy(reservationId = null , useStatus = UseStatus.AVAILABLE) })

        val users = userPort.queryAllUserById(request.users)
        userValidator.checkUsersUseStatus(users)

        reservationPort.saveReservation(reservation.copy(reason = request.reason))
        userPort.saveAll(users.map { it.copy(reservationId = reservationId) })
    }
}