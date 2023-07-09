package team.msg.hiv2.domain.homebase.presentation.data.web

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class ReservationHomeBaseWebRequest(
    @field:Size(min = 2, max = 6)
    val users: MutableList<UUID>,
    @field:NotBlank
    val reason: String
)
