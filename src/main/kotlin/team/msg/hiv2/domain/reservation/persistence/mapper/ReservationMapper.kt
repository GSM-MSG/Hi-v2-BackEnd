package team.msg.hiv2.domain.reservation.persistence.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.exception.HomeBaseNotFoundException
import team.msg.hiv2.domain.homebase.persistence.repository.HomeBaseRepository
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.persistence.entity.ReservationJpaEntity
import team.msg.hiv2.global.mapper.GenericMapper

@Component
class ReservationMapper(
    private val homeBaseRepository: HomeBaseRepository
) : GenericMapper<Reservation, ReservationJpaEntity> {

    override fun toDomain(entity: ReservationJpaEntity?): Reservation? =
        entity?.let {
            Reservation(
                id = it.id,
                homeBaseId = it.homeBase.id,
                reason = it.reason,
                checkStatus = it.checkStatus,
                reservationNumber = it.reservationNumber
            )
        }

    override fun toEntity(domain: Reservation): ReservationJpaEntity {
        val homeBase = homeBaseRepository.findByIdOrNull(domain.homeBaseId)
            ?: throw HomeBaseNotFoundException()
        return domain.let {
            ReservationJpaEntity(
                id = it.id,
                homeBase = homeBase,
                reason = it.reason,
                checkStatus = it.checkStatus,
                reservationNumber = it.reservationNumber
            )
        }
    }
}