package team.msg.hiv2.domain.homebase.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.application.spi.HomeBasePort
import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.homebase.persistence.mapper.HomeBaseMapper
import team.msg.hiv2.domain.homebase.persistence.repository.HomeBaseRepository

@Component
class HomeBasePersistenceAdapter(
    private val homeBaseRepository: HomeBaseRepository,
    private val homeBaseMapper: HomeBaseMapper
) : HomeBasePort {
    override fun queryHomeBaseByFloorAndPeriod(floor: Int, period: Int): List<HomeBase> =
        homeBaseRepository.findByFloorAndPeriod(floor, period).map { homeBaseMapper.toDomain(it)!! }

    override fun queryHomeBaseByFloorAndPeriodAndHomeBaseNumber(floor: Int, period: Int, homeBaseNumber: Int): HomeBase? =
        homeBaseMapper.toDomain(homeBaseRepository.findByFloorAndPeriodAndHomeBaseNumber(floor, period, homeBaseNumber))

    override fun queryAllHomeBaseByPeriod(period: Int): List<HomeBase> =
        homeBaseRepository.findAllByPeriod(period).map { homeBaseMapper.toDomain(it)!! }

    override fun queryHomeBaseById(id: Long): HomeBase? =
        homeBaseMapper.toDomain(homeBaseRepository.findByIdOrNull(id))

}