package team.msg.hiv2.domain.homebase.domain

import team.msg.hiv2.global.annotation.Aggregate
import java.time.LocalDateTime
import java.util.*

@Aggregate
data class HomeBase(
    val id: UUID,
    val floor: Int,
    val period: Int,
)