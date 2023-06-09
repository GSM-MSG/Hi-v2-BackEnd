package team.msg.hiv2.domain.user.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.response.UserInfoResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase
import java.util.*

@ReadOnlyUseCase
class QueryUserInfoUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService
) {

    fun execute(): UserInfoResponse {
        val user = userService.queryCurrentUser()

        val reservation = user.reservationId?.let { reservationService.queryReservationById(it) }

        val users = reservation?.let { userService.queryAllUserByReservation(it) }

        return UserInfoResponse(
            userId = UUID.randomUUID(),
            name = user.name,
            grade = user.grade,
            classNum = user.classNum,
            number = user.number,
            useStatus = user.useStatus,
            profileImageUrl = user.profileImageUrl,
            reservation = users?.let { users ->
                ReservationResponse(
                    reservationId = user.reservationId,
                    users = users.map {
                        UserResponse(
                            userId = it.id,
                            name = it.name,
                            grade = it.grade,
                            classNum = it.classNum,
                            number = it.number
                        )
                    }
                )
            }
        )
    }
}