package team.msg.hiv2.domain.homebase.application.facade

import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.application.usecase.DeleteAllReservationByPeriodUseCase
import team.msg.hiv2.domain.homebase.application.usecase.QueryReservationByHomeBaseUseCase
import team.msg.hiv2.domain.homebase.application.usecase.ReserveHomeBaseUseCase
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse

@Component
class HomeBaseFacadeImpl(
    private val deleteAllReservationByPeriodUseCase: DeleteAllReservationByPeriodUseCase,
    private val queryReservationByHomeBaseUseCase: QueryReservationByHomeBaseUseCase,
    private val reserveHomeBaseUseCase: ReserveHomeBaseUseCase
) : HomeBaseFacade {

    override fun deleteAllReservationByPeriod(period: Int) =
        deleteAllReservationByPeriodUseCase.execute(period)

    override fun queryReservationByHomeBase(floor: Int, period: Int): List<ReservationResponse?> =
        queryReservationByHomeBaseUseCase.execute(floor, period)

    override fun reserveHomeBase(floor: Int, period: Int, homeBaseNumber: Int, request: ReservationHomeBaseRequest) =
        reserveHomeBaseUseCase.execute(floor, period, homeBaseNumber, request)

}