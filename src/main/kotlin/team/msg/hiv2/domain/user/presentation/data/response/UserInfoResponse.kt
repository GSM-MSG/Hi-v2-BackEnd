package team.msg.hiv2.domain.user.presentation.data.response

import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus

class UserInfoResponse(
    val user: UserResponse,
    val useStatus: UseStatus,
    val reservation: ReservationResponse?
) {
    companion object {
        fun of(user: User, reservation: ReservationResponse?) = UserInfoResponse(
            user = UserResponse(
                userId = user.id,
                email = user.email,
                name = user.name,
                grade = user.grade,
                classNum = user.classNum,
                number = user.number,
                profileImageUrl = user.profileImageUrl,
                roles = user.roles
            ),
            useStatus = user.useStatus,
            reservation = reservation
        )
    }
}