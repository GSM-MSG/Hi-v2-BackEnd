package team.msg.hiv2.domain.homebase.presentation

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.msg.hiv2.domain.homebase.application.usecase.ReserveHomeBaseUseCase
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import javax.validation.Valid

@RestController
@RequestMapping("/homebase")
class HomeBaseWebAdapter(
    private val reserveHomeBaseUseCase: ReserveHomeBaseUseCase
) {

    @PostMapping
    fun reserveHomeBase(@RequestParam floor: Int,
                        @RequestParam period: Int,
                        @Valid @RequestBody request: ReservationHomeBaseRequest): ResponseEntity<Void> =
        reserveHomeBaseUseCase.execute(floor, period, request)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }


}