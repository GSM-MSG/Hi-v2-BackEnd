package team.msg.hiv2.domain.homebase.presentation.data.web

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class ReservationHomeBaseWebRequest(
    @field:Size(min = 2, max = 6)
    val users: MutableList<UUID>,
    @field:NotBlank
    val reason: String,
    @field:Min(1, message = "Value must be between 1 and 6")
    @field:Max(6, message = "Value must be between 1 and 6")
    val reservationNumber: Int
)
