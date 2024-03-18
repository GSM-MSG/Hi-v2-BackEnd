package team.msg.hiv2.domain.homebase.application.service

import team.msg.hiv2.domain.homebase.domain.HomeBase

interface QueryHomeBaseService {

    fun queryHomeBaseByFloorAndPeriodAndHomeBaseNumber(floor: Int, period: Int, homeBaseNumber: Int): HomeBase
    fun queryAllHomeBaseByPeriod(period: Int): List<HomeBase>
    fun queryHomeBaseById(id: Long): HomeBase
}