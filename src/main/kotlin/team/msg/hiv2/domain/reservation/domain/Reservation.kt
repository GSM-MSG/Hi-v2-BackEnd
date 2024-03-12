package team.msg.hiv2.domain.reservation.domain

import team.msg.hiv2.global.annotation.Aggregate
import java.util.*

@Aggregate
data class Reservation(
    val id: UUID,
    val homeBaseId: Long,
    val reason: String,
    val checkStatus: Boolean,
    val reservationNumber: Int
)
