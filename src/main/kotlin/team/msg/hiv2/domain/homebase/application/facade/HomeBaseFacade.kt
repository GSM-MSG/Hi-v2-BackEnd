package team.msg.hiv2.domain.homebase.application.facade

import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse

interface HomeBaseFacade {

    fun deleteAllReservationByPeriod(period: Int)
    fun queryReservationByHomeBase(floor: Int, period: Int): List<ReservationResponse?>
    fun reserveHomeBase(floor: Int, period: Int, homeBaseNumber: Int, request: ReservationHomeBaseRequest)
}