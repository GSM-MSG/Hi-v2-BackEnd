package team.msg.hiv2.domain.reservation.persistence.repository

import org.springframework.data.jpa.repository.*
import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity
import team.msg.hiv2.domain.reservation.persistence.entity.ReservationJpaEntity
import java.util.*
import javax.persistence.LockModeType.*

interface ReservationRepository : JpaRepository<ReservationJpaEntity, UUID> {

    @EntityGraph(attributePaths = ["homeBase"])
    fun findByHomeBaseId(homeBaseId: Long): ReservationJpaEntity?
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