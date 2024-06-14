package team.msg.hiv2.domain.reservation.presentation.data.web

import java.util.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateReservationWebRequest(
    @field:Size(min = 2, max = 6)
    val users: List<UUID>,
    @field:NotBlank
    val reason: String
)
