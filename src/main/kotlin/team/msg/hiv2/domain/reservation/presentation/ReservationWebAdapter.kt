package team.msg.hiv2.domain.reservation.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.hiv2.domain.reservation.application.usecase.DeleteReservationUseCase
import team.msg.hiv2.domain.reservation.application.usecase.QueryReservationDetailUseCase
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationDetailResponse
import java.util.UUID

@RestController
@RequestMapping("/reservation")
class ReservationWebAdapter(
    private val queryReservationDetailUseCase: QueryReservationDetailUseCase,
    private val deleteReservationUseCase: DeleteReservationUseCase
) {

    @GetMapping("/{id}")
    fun queryReservationDetail(@PathVariable id: UUID): ResponseEntity<ReservationDetailResponse> =
        queryReservationDetailUseCase.execute(id)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping("/{id}")
    fun deleteReservation(@PathVariable id: UUID): ResponseEntity<Void> =
        deleteReservationUseCase.execute(id)
            .let { ResponseEntity.noContent().build() }

}