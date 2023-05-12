package team.msg.hiv2.domain.homebase.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity
import java.util.UUID

interface HomeBaseRepository : CrudRepository<HomeBaseJpaEntity, Long>{
    fun findByFloorAndPeriod(floor: Int, period: Int): HomeBaseJpaEntity?
}