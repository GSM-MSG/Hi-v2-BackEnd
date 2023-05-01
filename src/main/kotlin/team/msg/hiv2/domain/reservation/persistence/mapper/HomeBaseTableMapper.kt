package team.msg.hiv2.domain.reservation.persistence.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.exception.HomeBaseNotFoundException
import team.msg.hiv2.domain.homebase.persistence.repository.HomeBaseRepository
import team.msg.hiv2.domain.reservation.domain.HomeBaseTable
import team.msg.hiv2.domain.reservation.persistence.entity.HomeBaseTableJpaEntity
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.domain.user.persistence.repository.UserRepository
import team.msg.hiv2.global.mapper.GenericMapper

@Component
class HomeBaseTableMapper(
    private val userRepository: UserRepository,
    private val homeBaseRepository: HomeBaseRepository
) : GenericMapper<HomeBaseTable, HomeBaseTableJpaEntity>{

    override fun toDomain(entity: HomeBaseTableJpaEntity?): HomeBaseTable? =
        entity?.let {
            HomeBaseTable(
                id = it.id,
                representativeId = it.representative.id,
                reason = it.reason,
                homeBaseId = it.homeBase.id,
                checkStatus = it.checkStatus
            )
        }

    override fun toEntity(domain: HomeBaseTable): HomeBaseTableJpaEntity {
        val user = userRepository.findByIdOrNull(domain.representativeId) ?: throw UserNotFoundException()
        val homeBase = homeBaseRepository.findByIdOrNull(domain.homeBaseId) ?: throw HomeBaseNotFoundException()
        return domain.let {
            HomeBaseTableJpaEntity(
                id = it.id,
                representative = user,
                reason = it.reason,
                checkStatus = it.checkStatus,
                homeBase = homeBase,
                deletedAt = it.deletedAt
            )
        }
    }

}