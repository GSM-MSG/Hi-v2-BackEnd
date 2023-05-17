package team.msg.hiv2.domain.reservation.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.hiv2.domain.homebase.application.usecase.QueryReservationDetailUseCase
import team.msg.hiv2.domain.homebase.presentation.data.response.ReservationDetailResponse
import java.util.UUID

@RestController
@RequestMapping("/reservation")
class ReservationWebAdapter(
    private val queryReservationDetailUseCase: QueryReservationDetailUseCase
) {

    @GetMapping("/{id}")
    fun queryReservationById(@PathVariable id: UUID): ResponseEntity<ReservationDetailResponse> =
        queryReservationDetailUseCase.execute(id)
            .let { ResponseEntity.ok(it) }
}