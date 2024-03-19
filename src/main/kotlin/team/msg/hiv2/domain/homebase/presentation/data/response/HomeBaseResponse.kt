package team.msg.hiv2.domain.homebase.presentation.data.response

import team.msg.hiv2.domain.homebase.domain.HomeBase

data class HomeBaseResponse(
    val homeBaseId: Long,
    val floor: Int,
    val period: Int,
    val homeBaseNumber: Int,
    val maxCapacity: Int
) {
    companion object {
        fun of(homeBase: HomeBase) = HomeBaseResponse(
            homeBaseId = homeBase.id,
            floor = homeBase.floor,
            period = homeBase.period,
            homeBaseNumber = homeBase.homeBaseNumber,
            maxCapacity = homeBase.maxCapacity
        )
    }
}