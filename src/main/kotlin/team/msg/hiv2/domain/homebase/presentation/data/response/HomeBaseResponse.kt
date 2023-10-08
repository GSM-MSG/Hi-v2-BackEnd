package team.msg.hiv2.domain.homebase.presentation.data.response

import team.msg.hiv2.domain.homebase.domain.HomeBase

data class HomeBaseResponse(
    val homeBaseId: Long,
    val floor: Int,
    val period: Int
) {
    companion object {
        fun of(homeBase: HomeBase): HomeBaseResponse =
            HomeBaseResponse(
                homeBaseId = homeBase.id,
                floor = homeBase.floor,
                period = homeBase.period
            )
    }
}