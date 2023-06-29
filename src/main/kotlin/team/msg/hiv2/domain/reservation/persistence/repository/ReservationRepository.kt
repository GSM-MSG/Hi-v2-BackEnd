package team.msg.hiv2.domain.reservation.persistence.repository

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity
import team.msg.hiv2.domain.reservation.persistence.entity.ReservationJpaEntity
import java.util.UUID

interface ReservationRepository : CrudRepository<ReservationJpaEntity, UUID> {
    fun findAllByHomeBase(homeBase: HomeBaseJpaEntity): List<ReservationJpaEntity>
    fun findAllByHomeBaseIn(homeBases: List<HomeBaseJpaEntity>): List<ReservationJpaEntity>
    @Modifying
    @Query("DELETE FROM ReservationJpaEntity r WHERE r IN :reservations")
    fun deleteAllInBatch(reservations: List<ReservationJpaEntity>)
    fun countByHomeBase(homeBase: HomeBaseJpaEntity): Int
}