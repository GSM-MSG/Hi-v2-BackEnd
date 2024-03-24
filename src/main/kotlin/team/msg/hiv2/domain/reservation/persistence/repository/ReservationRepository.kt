package team.msg.hiv2.domain.reservation.persistence.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity
import team.msg.hiv2.domain.reservation.persistence.entity.ReservationJpaEntity
import java.util.*

interface ReservationRepository : JpaRepository<ReservationJpaEntity, UUID> {

    @EntityGraph(attributePaths = ["homeBase"])
    fun findByHomeBase(homeBase: HomeBaseJpaEntity): ReservationJpaEntity?
    @EntityGraph(attributePaths = ["homeBase"])
    fun findAllByHomeBaseIn(homeBases: List<HomeBaseJpaEntity>): List<ReservationJpaEntity>
    @Modifying
    @Query("DELETE FROM ReservationJpaEntity r WHERE r IN :reservations")
    fun deleteAllInBatch(reservations: List<ReservationJpaEntity>)
    fun countByHomeBase(homeBase: HomeBaseJpaEntity): Int
    fun existsByHomeBase(homeBase: HomeBaseJpaEntity): Boolean
    @EntityGraph(attributePaths = ["homeBase"])
    fun findAllByTeamInOrderByHomeBaseId(teams: List<TeamJpaEntity>): List<ReservationJpaEntity>
}