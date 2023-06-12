package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.QueryReservationService
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationDetailResponse
import team.msg.hiv2.domain.reservation.application.spi.QueryReservationPort
import team.msg.hiv2.domain.reservation.exception.ReservationNotFoundException
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase
import java.util.UUID

@ReadOnlyUseCase
class QueryReservationDetailUseCase(
    private val queryReservationService: QueryReservationService,
    private val queryUserService: QueryUserService
) {

    fun execute(id: UUID): ReservationDetailResponse {
        val reservation = queryReservationService.queryReservationById(id)
        val users = queryUserService.queryAllUserByReservation(reservation)

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