package team.msg.hiv2.domain.reservation.presentation

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.hiv2.domain.reservation.application.facade.ReservationFacade
import team.msg.hiv2.domain.reservation.application.usecase.*
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationCheckStatusRequest
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationDetailResponse
import team.msg.hiv2.domain.reservation.presentation.data.web.UpdateReservationCheckStatusWebRequest
import team.msg.hiv2.domain.reservation.presentation.data.web.UpdateReservationWebRequest
import java.util.*

@RestController
@RequestMapping("/reservation")
class ReservationWebAdapter(
    private val reservationFacade: ReservationFacade
) {

    @GetMapping("/{id}")
    fun queryReservationDetail(@PathVariable id: UUID): ResponseEntity<ReservationDetailResponse> =
        reservationFacade.queryReservationDetail(id)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping("/{id}")
    fun deleteReservation(@PathVariable id: UUID): ResponseEntity<Unit> =
        reservationFacade.deleteReservation(id)
            .let { ResponseEntity.noContent().build() }

    @PatchMapping("/{id}")
    fun updateReservation(@PathVariable id: UUID, @RequestBody request: UpdateReservationWebRequest): ResponseEntity<Unit> =
        reservationFacade.updateReservation(id,
            UpdateReservationRequest(
                users = request.users,
                reason = request.reason
            )
        )
            .let { ResponseEntity.noContent().build() }

    @DeleteMapping("/{id}/exit")
    fun exitReservation(@PathVariable id: UUID): ResponseEntity<Unit> =
        reservationFacade.exitReservation(id)
            .let { ResponseEntity.noContent().build() }

    @PatchMapping("/{id}/check-status")
    fun updateReservationCheckStatus(@PathVariable id: UUID,
                                     @RequestBody @Valid request: UpdateReservationCheckStatusWebRequest): ResponseEntity<Unit> =
        reservationFacade.updateReservationCheckStatus(id,
            UpdateReservationCheckStatusRequest(
                request.checkStatus
            )
        )
            .let { ResponseEntity.noContent().build() }

    @DeleteMapping("/kill-all")
    fun deleteAllReservation(): ResponseEntity<Unit> =
        reservationFacade.deleteAllReservation()
            .let { ResponseEntity.noContent().build() }

}