package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.reservation.application.spi.QueryReservationPort
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.presentation.data.response.UserInfoResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase
import java.util.*

@ReadOnlyUseCase
class QueryUserInfoUseCase(
    private val queryUserPort: QueryUserPort,
    private val queryReservationPort: QueryReservationPort
) {
    fun execute(): UserInfoResponse {
        val user = queryUserPort.queryCurrentUser()
        val reservation = user.reservationId?.let { queryReservationPort.queryReservationById(it) }
        val users = reservation?.let { queryUserPort.queryAllUserByReservation(it) }

        return UserInfoResponse(
            id = UUID.randomUUID(),
            name = user.name,
            grade = user.grade,
            classNum = user.classNum,
            number = user.number,
            useStatus = user.useStatus,
            profileImageUrl = user.profileImageUrl,
            reservation = ReservationResponse(
                reservationId = user.reservationId,
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