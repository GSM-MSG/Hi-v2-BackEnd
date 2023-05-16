package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.spi.QueryHomeBasePort
import team.msg.hiv2.domain.homebase.exception.HomeBaseNotFoundException
import team.msg.hiv2.domain.reservation.application.spi.QueryReservationPort
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryReservationUseCase(
    private val queryReservationPort: QueryReservationPort,
    private val queryHomeBasePort: QueryHomeBasePort
) {

    fun execute(floor: Int, period: Int){
        val homeBase = queryHomeBasePort.queryHomeBaseByFloorAndPeriod(floor, period)
            .let { it ?: throw HomeBaseNotFoundException() }

    }
}