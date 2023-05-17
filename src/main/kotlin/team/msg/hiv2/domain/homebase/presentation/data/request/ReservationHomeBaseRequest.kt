package team.msg.hiv2.domain.homebase.presentation.data.request

import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class ReservationHomeBaseRequest(
    @field:Size(min = 2, max = 6)
    val users: MutableList<UUID>,
    @field:NotBlank
    val reason: String
)
