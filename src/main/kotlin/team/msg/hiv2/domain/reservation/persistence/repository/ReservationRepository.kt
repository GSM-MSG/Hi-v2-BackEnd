package team.msg.hiv2.domain.reservation.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity
import team.msg.hiv2.domain.reservation.persistence.entity.ReservationJpaEntity
import java.util.UUID

interface ReservationRepository : CrudRepository<ReservationJpaEntity, UUID> {
    fun findByHomeBase(homeBase: HomeBaseJpaEntity): ReservationJpaEntity?
}