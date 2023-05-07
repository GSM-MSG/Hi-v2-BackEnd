package team.msg.hiv2.domain.homebase.persistence.mapper

import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity
import team.msg.hiv2.global.mapper.GenericMapper

@Component
class HomeBaseMapper : GenericMapper<HomeBase, HomeBaseJpaEntity>{

    override fun toDomain(entity: HomeBaseJpaEntity?): HomeBase? =
        entity?.let{
            HomeBase(
                id = it.id,
                floor = it.floor,
                period = it.period
            )
        }

    override fun toEntity(domain: HomeBase): HomeBaseJpaEntity =
        domain.let {
            HomeBaseJpaEntity(
                id = it.id,
                floor = it.floor,
                period = it.period
            )
        }
}