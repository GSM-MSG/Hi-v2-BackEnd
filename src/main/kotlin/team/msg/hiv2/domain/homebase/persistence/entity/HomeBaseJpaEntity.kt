package team.msg.hiv2.domain.homebase.persistence.entity

import team.msg.hiv2.global.entity.BaseIdEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "home_base")
class HomeBaseJpaEntity(

    override val id: Long,

    @Column(name = "floor", nullable = false)
    val floor: Int,

    @Column(name = "period", nullable = false)
    val period: Int,

    @Column(name = "home_base_number", nullable = false)
    val homeBaseNumber: Int,

    @Column(name = "max_capacity", nullable = false)
    val maxCapacity: Int

) : BaseIdEntity(id)