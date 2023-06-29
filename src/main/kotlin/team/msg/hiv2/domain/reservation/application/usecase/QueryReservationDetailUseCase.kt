package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationDetailResponse
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase
import java.util.UUID

@ReadOnlyUseCase
class QueryReservationDetailUseCase(
    private val reservationService: ReservationService,
    private val userService: UserService
) {

    fun execute(id: UUID): ReservationDetailResponse {
        val reservation = reservationService.queryReservationById(id)

        val users = userService.queryAllUserByReservation(reservation)

        return ReservationDetailResponse(
            reservationId = reservation.id,
            users = users.map {
                UserResponse(
                    userId = it.id,
                    name = it.name,
                    grade = it.grade,
                    classNum = it.classNum,
                    number = it.number
                )
            },
            reason = reservation.reason,
            checkStatus = reservation.checkStatus
        )
    }
}