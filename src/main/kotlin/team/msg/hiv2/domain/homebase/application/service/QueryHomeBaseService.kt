package team.msg.hiv2.domain.homebase.application.service

import team.msg.hiv2.domain.homebase.domain.HomeBase

interface QueryHomeBaseService {

    fun queryHomeBaseByFloorAndPeriod(floor: Int, period: Int): HomeBase
    fun queryAllHomeBaseByPeriod(period: Int): List<HomeBase>
}