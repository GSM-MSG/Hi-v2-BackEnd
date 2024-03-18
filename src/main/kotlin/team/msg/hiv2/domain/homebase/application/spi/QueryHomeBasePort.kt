package team.msg.hiv2.domain.homebase.application.spi

import team.msg.hiv2.domain.homebase.domain.HomeBase

interface QueryHomeBasePort {

    fun queryHomeBaseByFloorAndPeriodAndHomeBaseNumber(floor: Int, period: Int, homeBaseNumber: Int): HomeBase?
    fun queryAllHomeBaseByPeriod(period: Int): List<HomeBase>
    fun queryHomeBaseById(id: Long): HomeBase?
}