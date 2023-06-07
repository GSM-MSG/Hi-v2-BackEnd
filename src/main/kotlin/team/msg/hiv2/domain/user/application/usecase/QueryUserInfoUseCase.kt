package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.reservation.application.spi.QueryReservationPort
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.presentation.data.response.UserInfoResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryUserInfoUseCase(
    private val queryUserPort: QueryUserPort,
    private val queryReservationPort: QueryReservationPort
) {
    fun execute(): UserInfoResponse {
        val user = queryUserPort.queryCurrentUser()
        val reservation = queryReservationPort.queryReservationByUser(user)
        val users = reservation?.let { queryUserPort.queryAllUserByReservation(it) }

        return UserInfoResponse(
            user = UserResponse(
                userId = user.id,
                name = user.name,
                grade = user.grade,
                classNum = user.classNum,
                number = user.number
            ),
            reservation = ReservationResponse(
                reservationId = reservation?.id,
                users = users?.map {
                    UserResponse(
                        userId = it.id,
                        name = it.name,
                        grade = it.grade,
                        classNum = it.classNum,
                        number = it.number
                    )
                }
            )
        )
    }
}