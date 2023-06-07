package team.msg.hiv2.domain.user.presentation.data.response

import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse

class UserInfoResponse(
    val user: UserResponse,
    val reservation: ReservationResponse?
)