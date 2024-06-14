package team.msg.hiv2.domain.reservation.presentation.data.web

import jakarta.validation.constraints.NotNull

data class UpdateReservationCheckStatusWebRequest(
    @field:NotNull
    val checkStatus: Boolean
)
