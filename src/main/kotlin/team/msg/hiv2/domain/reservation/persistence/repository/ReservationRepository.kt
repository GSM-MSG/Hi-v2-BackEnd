package team.msg.hiv2.domain.reservation.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity
import team.msg.hiv2.domain.reservation.persistence.entity.ReservationJpaEntity
import team.msg.hiv2.domain.user.persistence.entity.UserJpaEntity
import java.util.UUID

interface ReservationRepository : CrudRepository<ReservationJpaEntity, UUID> {
    fun findAllByHomeBase(homeBase: HomeBaseJpaEntity): List<ReservationJpaEntity>
    fun findAllByHomeBaseContaining(homeBases: List<HomeBaseJpaEntity>): List<ReservationJpaEntity>
    fun deleteAll(reservations: List<ReservationJpaEntity>)
}