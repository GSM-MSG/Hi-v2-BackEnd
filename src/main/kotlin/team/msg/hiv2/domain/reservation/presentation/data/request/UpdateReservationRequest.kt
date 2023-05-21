package team.msg.hiv2.domain.reservation.presentation.data.request

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UpdateReservationRequest(
    @field:Size(min = 2, max = 6)
    val users: MutableList<UUID>,
    @field:NotBlank
    val reason: String
)
