package team.msg.hiv2.domain.reservation.persistence.mapper

import org.springframework.data.repository.findByIdOrNull
import team.msg.hiv2.domain.reservation.domain.HomeBaseTable
import team.msg.hiv2.domain.reservation.persistence.entity.HomeBaseTableJpaEntity
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.domain.user.persistence.repository.UserRepository
import team.msg.hiv2.global.mapper.GenericMapper

class HomeBaseTableMapper(
    private val userRepository: UserRepository
) : GenericMapper<HomeBaseTable, HomeBaseTableJpaEntity>{

    override fun toDomain(entity: HomeBaseTableJpaEntity?): HomeBaseTable? =
        entity?.let {
            HomeBaseTable(
                id = it.id,
                representativeId = it.representative.id,
                reason = it.reason,
                checkStatus = it.checkStatus
            )
        }

    override fun toEntity(domain: HomeBaseTable): HomeBaseTableJpaEntity {
        val user = userRepository.findByIdOrNull(domain.representativeId) ?: throw UserNotFoundException()

        return domain.let {
            HomeBaseTableJpaEntity(
                id = it.id,
                representative = user,
                reason = it.reason,
                checkStatus = it.checkStatus,
                deletedAt = it.deletedAt
            )
        }
    }

}