package team.msg.hiv2.domain.homebase.persistence.entity

import team.msg.hiv2.global.entity.BaseIdEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "home_base")
class HomeBaseJpaEntity(

    override val id: Long,

    @Column(columnDefinition = "TINYINT UNSIGNED", nullable = false)
    val floor: Int,

    @Column(columnDefinition = "TINYINT UNSIGNED", nullable = false)
    val period: Int,

    @Column(columnDefinition = "TINYINT UNSIGNED", nullable = false)
    val homeBaseNumber: Int,

    @Column(columnDefinition = "TINYINT UNSIGNED", nullable = false)
    val maxCapacity: Int

) : BaseIdEntity(id)