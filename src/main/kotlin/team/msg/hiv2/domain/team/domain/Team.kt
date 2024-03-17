package team.msg.hiv2.domain.team.domain

import java.util.*

data class Team(
    val id: UUID,
    val userIds: MutableList<UUID>
)