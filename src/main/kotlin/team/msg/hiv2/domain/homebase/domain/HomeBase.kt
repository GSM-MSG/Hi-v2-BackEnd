package team.msg.hiv2.domain.homebase.domain

import team.msg.hiv2.global.annotation.Aggregate

@Aggregate
data class HomeBase(
    val id: Long,
    val floor: Int,
    val period: Int,
    val number: Int,
    val maxCapacity: Int
)