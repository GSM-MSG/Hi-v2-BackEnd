package team.msg.hiv2.domain.homebase.application.service

import team.msg.hiv2.domain.homebase.application.spi.QueryHomeBasePort
import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.homebase.exception.HomeBaseNotFoundException
import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class QueryHomeBaseServiceImpl(
    private val queryHomeBasePort: QueryHomeBasePort
) : QueryHomeBaseService {

    override fun queryHomeBaseByFloorAndPeriodAndHomeBaseNumber(floor: Int, period: Int, homeBaseNumber: Int): HomeBase =
        queryHomeBasePort.queryHomeBaseByFloorAndPeriodAndHomeBaseNumber(floor, period, homeBaseNumber) ?: throw HomeBaseNotFoundException()

    override fun queryAllHomeBaseByPeriod(period: Int): List<HomeBase> =
        queryHomeBasePort.queryAllHomeBaseByPeriod(period)

    override fun queryHomeBaseById(id: Long): HomeBase =
        queryHomeBasePort.queryHomeBaseById(id) ?: throw HomeBaseNotFoundException()
}