package team.msg.hiv2.domain.homebase.application.spi

import team.msg.hiv2.domain.homebase.domain.HomeBase

interface QueryHomeBasePort {

    fun queryHomeBaseByFloorAndPeriod(floor: Int, period: Int): HomeBase?
}