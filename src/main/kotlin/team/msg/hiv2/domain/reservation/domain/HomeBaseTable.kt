package team.msg.hiv2.domain.reservation.domain

import team.msg.hiv2.domain.reservation.domain.constant.CheckStatus
import team.msg.hiv2.global.annotation.Aggregate
import java.util.UUID

@Aggregate
class HomeBaseTable(
    val id: UUID,
    val userId: UUID,
    val reason: String,
    val checkStatus: CheckStatus
)