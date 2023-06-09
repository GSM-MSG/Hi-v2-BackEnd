package team.msg.hiv2.domain.user.persistence.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.reservation.persistence.repository.ReservationRepository
import team.msg.hiv2.domain.user.persistence.entity.UserJpaEntity
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.global.mapper.GenericMapper

@Component
class UserMapper(
    private val reservationRepository: ReservationRepository
) : GenericMapper<User, UserJpaEntity> {

    override fun toDomain(entity: UserJpaEntity?): User? =
        entity?.let {
            User(
                id = it.id,
                email = it.email,
                name = it.name,
                grade = it.grade,
                classNum = it.classNum,
                number = it.number,
                profileImageUrl = it.profileImageUrl,
                roles = it.roles,
                reservationId = it.reservation?.id,
                useStatus = it.useStatus
            )
        }

    override fun toEntity(domain: User): UserJpaEntity {
        val reservationId = domain.reservationId
        val reservation = reservationId?.let { reservationRepository.findByIdOrNull(it) }
        return domain.let {
            UserJpaEntity(
                id = it.id,
                email = it.email,
                name = it.name,
                grade = it.grade,
                classNum = it.classNum,
                number = it.number,
                profileImageUrl = it.profileImageUrl,
                roles = it.roles,
                reservation = reservation,
                useStatus = it.useStatus
            )
        }
    }
}