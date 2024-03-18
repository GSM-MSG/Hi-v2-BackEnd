package team.msg.hiv2.domain.reservation.persistence.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.exception.HomeBaseNotFoundException
import team.msg.hiv2.domain.homebase.persistence.repository.HomeBaseRepository
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.persistence.entity.ReservationJpaEntity
import team.msg.hiv2.domain.team.exception.TeamNotFoundException
import team.msg.hiv2.domain.team.persistence.repository.TeamRepository
import team.msg.hiv2.global.mapper.GenericMapper

@Component
class ReservationMapper(
    private val homeBaseRepository: HomeBaseRepository,
    private val teamRepository: TeamRepository
) : GenericMapper<Reservation, ReservationJpaEntity> {

    override fun toDomain(entity: ReservationJpaEntity?): Reservation? =
        entity?.let {
            Reservation(
                id = it.id,
                homeBaseId = it.homeBase.id,
                teamId = it.team.id,
                reason = it.reason,
                checkStatus = it.checkStatus
            )
        }

    override fun toEntity(domain: Reservation): ReservationJpaEntity {
        val homeBase = homeBaseRepository.findByIdOrNull(domain.homeBaseId) ?: throw HomeBaseNotFoundException()
        val team = teamRepository.findByIdOrNull(domain.teamId) ?: throw TeamNotFoundException()

        return domain.let {
            ReservationJpaEntity(
                id = it.id,
                homeBase = homeBase,
                team = team,
                reason = it.reason,
                checkStatus = it.checkStatus
            )
        }
    }
}