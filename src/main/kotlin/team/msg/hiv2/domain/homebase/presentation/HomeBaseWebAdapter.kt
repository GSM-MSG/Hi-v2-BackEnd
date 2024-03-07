package team.msg.hiv2.domain.homebase.presentation

import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.msg.hiv2.domain.homebase.application.facade.HomeBaseFacade
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import team.msg.hiv2.domain.homebase.presentation.data.web.ReservationHomeBaseWebRequest
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse

@RestController
@RequestMapping("/homebase")
class HomeBaseWebAdapter(
    private val homeBaseFacade: HomeBaseFacade
) {

    @PostMapping
    fun reserveHomeBase(@RequestParam floor: Int,
                        @RequestParam period: Int,
                        @Valid @RequestBody request: ReservationHomeBaseWebRequest): ResponseEntity<Void> =
        homeBaseFacade.reserveHomeBase(floor, period, ReservationHomeBaseRequest(request.users, request.reason, request.reservationNumber))
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping
    fun queryHomeBaseReservation(@RequestParam floor: Int, @RequestParam period: Int): ResponseEntity<List<ReservationResponse>> =
        homeBaseFacade.queryReservationByHomeBase(floor, period)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping
    fun deleteAllReservationByPeriod(@RequestParam period: Int): ResponseEntity<Void> =
        homeBaseFacade.deleteAllReservationByPeriod(period)
            .let { ResponseEntity.noContent().build() }
}