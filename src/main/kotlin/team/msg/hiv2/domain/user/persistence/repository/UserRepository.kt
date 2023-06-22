package team.msg.hiv2.domain.user.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.reservation.persistence.entity.ReservationJpaEntity
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.persistence.entity.UserJpaEntity
import java.util.*

interface UserRepository : CrudRepository<UserJpaEntity, UUID> {
    fun findByEmail(email: String): UserJpaEntity?
    fun existsByEmail(email: String): Boolean
    fun findAllByReservation(reservation: ReservationJpaEntity): List<UserJpaEntity>
    fun findByIdAndReservation(id: UUID, reservation: ReservationJpaEntity): UserJpaEntity
    fun findAllByNameContaining(keyword: String): List<UserJpaEntity>
    fun findAllByReservationIsNotNull(): List<UserJpaEntity>
    fun findAllByRolesContaining(role: UserRole): List<UserJpaEntity>
    fun findAllByReservationIn(reservations: List<ReservationJpaEntity>): List<UserJpaEntity>
}