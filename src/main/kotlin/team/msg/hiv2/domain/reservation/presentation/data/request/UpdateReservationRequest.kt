package team.msg.hiv2.domain.reservation.presentation.data.request

import java.util.*

data class UpdateReservationRequest(
    val users: List<UUID>,
    val reason: String
)
