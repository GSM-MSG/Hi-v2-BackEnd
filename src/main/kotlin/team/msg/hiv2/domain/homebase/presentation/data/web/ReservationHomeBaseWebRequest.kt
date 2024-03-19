package team.msg.hiv2.domain.homebase.presentation.data.web

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import java.util.*

data class ReservationHomeBaseWebRequest(
    @field:Size(min = 2, max = 6)
    val users: MutableList<UUID>,
    @field:NotBlank
    val reason: String
)
