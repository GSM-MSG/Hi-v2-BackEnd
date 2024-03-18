package team.msg.hiv2.domain.reservation.application.facade

import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationCheckStatusRequest
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationDetailResponse
import java.util.UUID

interface ReservationFacade {

    fun checkAndRestrictReservation()
    fun deleteAllReservation()
    fun deleteReservation(id: UUID)
    fun exitReservation(id: UUID)
    fun queryReservationDetail(id: UUID): ReservationDetailResponse
    fun updateReservationCheckStatus(id: UUID, request: UpdateReservationCheckStatusRequest)
    fun updateReservation(id: UUID, request: UpdateReservationRequest)
}