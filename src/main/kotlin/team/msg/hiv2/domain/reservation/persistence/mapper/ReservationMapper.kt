package team.msg.hiv2.domain.reservation.persistence.mapper

import org.springframework.data.repository.findByIdOrNull
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.exception.HomeBaseTableNotFoundException
import team.msg.hiv2.domain.reservation.persistence.entity.ReservationJpaEntity
import team.msg.hiv2.domain.reservation.persistence.repository.HomeBaseTableRepository
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.domain.user.persistence.repository.UserRepository
import team.msg.hiv2.global.mapper.GenericMapper

class ReservationMapper(
    private val userRepository: UserRepository,
    private val homeBaseTableRepository: HomeBaseTableRepository
) : GenericMapper<Reservation, ReservationJpaEntity> {

    override fun toDomain(entity: ReservationJpaEntity?): Reservation? =
        entity?.let {
            Reservation(
                id = it.id,
                userId = it.user.id,
                homeBaseTableId = it.homeBaseTable.id,
                deletedAt = it.deletedAt
            )
        }

    override fun toEntity(domain: Reservation): ReservationJpaEntity {
        val user = userRepository.findByIdOrNull(domain.userId)
            ?: throw UserNotFoundException()
        val homeBaseTable = homeBaseTableRepository.findByIdOrNull(domain.homeBaseTableId)
            ?: throw HomeBaseTableNotFoundException()

        return domain.let {
            ReservationJpaEntity(
                id = it.id,
                user = user,
                homeBaseTable = homeBaseTable,
                deletedAt = it.deletedAt
            )
        }
    }
}