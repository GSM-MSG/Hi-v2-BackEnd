package team.msg.hiv2.domain.homebase.presentation.data.request

import java.util.UUID

data class ReservationHomeBaseRequest(
    val members: MutableList<UUID>,
    val reason: String
)
