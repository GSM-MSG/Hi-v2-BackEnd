package team.msg.hiv2.domain.homebase.persistence.entity.id

import java.io.Serializable

data class HomeBaseId(
    val floor: Int,
    val period: Int,
    val homeBaseNumber: Int
) : Serializable