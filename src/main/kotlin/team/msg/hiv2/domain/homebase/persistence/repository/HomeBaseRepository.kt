package team.msg.hiv2.domain.homebase.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity

interface HomeBaseRepository : CrudRepository<HomeBaseJpaEntity, Long> {

    fun findByFloorAndPeriod(floor: Int, period: Int): List<HomeBaseJpaEntity>
    fun findByFloorAndPeriodAndHomeBaseNumber(floor: Int, period: Int, homeBaseNumber: Int): HomeBaseJpaEntity?
    fun findAllByPeriod(period: Int): List<HomeBaseJpaEntity>
}