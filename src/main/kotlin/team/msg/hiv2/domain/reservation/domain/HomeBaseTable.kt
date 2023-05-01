package team.msg.hiv2.domain.reservation.domain

import team.msg.hiv2.domain.reservation.domain.constant.CheckStatus
import team.msg.hiv2.global.annotation.Aggregate
import java.time.LocalDateTime
import java.util.UUID

@Aggregate
class HomeBaseTable(
    val id: UUID,
    val representativeId: UUID,
    val reason: String,
    val checkStatus: CheckStatus,
    val homeBaseId: UUID,
    val deletedAt: LocalDateTime? = null
)