package team.msg.hiv2.domain.reservation.persistence.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity
import team.msg.hiv2.domain.reservation.persistence.entity.ReservationJpaEntity
import java.util.*
import javax.persistence.LockModeType.*
import javax.persistence.QueryHint
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.QueryHints

interface ReservationRepository : JpaRepository<ReservationJpaEntity, UUID> {

    @EntityGraph(attributePaths = ["homeBase"])
    fun findByHomeBase(homeBase: HomeBaseJpaEntity): ReservationJpaEntity?
    @EntityGraph(attributePaths = ["homeBase"])
    fun findAllByHomeBaseIn(homeBases: List<HomeBaseJpaEntity>): List<ReservationJpaEntity>
    @Modifying
    @Query("DELETE FROM ReservationJpaEntity r WHERE r IN :reservations")
    fun deleteAllInBatch(reservations: List<ReservationJpaEntity>)
    fun countByHomeBase(homeBase: HomeBaseJpaEntity): Int
    @Lock(PESSIMISTIC_WRITE)
    fun existsByHomeBaseId(homeBaseId: Long): Boolean
    @EntityGraph(attributePaths = ["homeBase"])
    fun findAllByUserIdsInOrderByHomeBaseId(userIds: List<UUID>): List<ReservationJpaEntity>
}