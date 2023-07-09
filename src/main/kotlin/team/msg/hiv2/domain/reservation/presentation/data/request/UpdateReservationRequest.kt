package team.msg.hiv2.domain.reservation.presentation.data.request

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UpdateReservationRequest(
    val users: List<UUID>,
    val reason: String
)
