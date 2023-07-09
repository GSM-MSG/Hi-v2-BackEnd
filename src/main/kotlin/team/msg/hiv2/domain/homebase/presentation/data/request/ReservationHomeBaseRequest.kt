package team.msg.hiv2.domain.homebase.presentation.data.request

import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class ReservationHomeBaseRequest(
    val users: MutableList<UUID>,
    val reason: String
)
