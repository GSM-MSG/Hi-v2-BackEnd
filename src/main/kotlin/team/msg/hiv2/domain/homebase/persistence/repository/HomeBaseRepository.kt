package team.msg.hiv2.domain.homebase.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity
import team.msg.hiv2.domain.homebase.persistence.entity.id.HomeBaseId

interface HomeBaseRepository : CrudRepository<HomeBaseJpaEntity, HomeBaseId> {

    fun findByFloorAndPeriodAndHomeBaseNumber(floor: Int, period: Int, homeBaseNumber: Int): HomeBaseJpaEntity?
    fun findAllByPeriod(period: Int): List<HomeBaseJpaEntity>
}