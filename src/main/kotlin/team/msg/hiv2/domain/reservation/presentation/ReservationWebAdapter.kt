package team.msg.hiv2.domain.reservation.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.hiv2.domain.reservation.application.usecase.*
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationCheckStatusRequest
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationDetailResponse
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/reservation")
class ReservationWebAdapter(
    private val queryReservationDetailUseCase: QueryReservationDetailUseCase,
    private val deleteReservationUseCase: DeleteReservationUseCase,
    private val updateReservationUseCase: UpdateReservationUseCase,
    private val delegateRepresentativeUseCase: DelegateRepresentativeUseCase,
    private val exitReservationUseCase: ExitReservationUseCase,
    private val updateReservationCheckStatusUseCase: UpdateReservationCheckStatusUseCase
) {

    @GetMapping("/{id}")
    fun queryReservationDetail(@PathVariable id: UUID): ResponseEntity<ReservationDetailResponse> =
        queryReservationDetailUseCase.execute(id)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping("/{id}")
    fun deleteReservation(@PathVariable id: UUID): ResponseEntity<Void> =
        deleteReservationUseCase.execute(id)
            .let { ResponseEntity.noContent().build() }

    @PatchMapping("/{id}")
    fun updateReservation(@PathVariable id: UUID, request: UpdateReservationRequest): ResponseEntity<Void> =
        updateReservationUseCase.execute(id, request)
            .let { ResponseEntity.noContent().build() }

    @PatchMapping("/{id}/{user_id}")
    fun delegateRepresentative(@PathVariable id: UUID, @PathVariable("user_id") userId: UUID): ResponseEntity<Void> =
        delegateRepresentativeUseCase.execute(id, userId)
            .let { ResponseEntity.noContent().build() }

    @DeleteMapping("/{id}/exit")
    fun exitReservation(@PathVariable id: UUID): ResponseEntity<Void> =
        exitReservationUseCase.execute(id)
            .let { ResponseEntity.noContent().build() }

    @PatchMapping("/{id}/check-status")
    fun updateReservationCheckStatus(@PathVariable id: UUID,
                                     @RequestBody @Valid request: UpdateReservationCheckStatusRequest): ResponseEntity<Void> =
        updateReservationCheckStatusUseCase.execute(id, request)
            .let { ResponseEntity.noContent().build() }


}