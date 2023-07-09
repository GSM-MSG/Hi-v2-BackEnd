package team.msg.hiv2.domain.reservation.presentation.data.web

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UpdateReservationWebRequest(
    @field:Size(min = 2, max = 6)
    val users: List<UUID>,
    @field:NotBlank
    val reason: String
)
