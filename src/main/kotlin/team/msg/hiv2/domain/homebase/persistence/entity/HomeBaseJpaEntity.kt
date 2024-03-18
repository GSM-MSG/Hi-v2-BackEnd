package team.msg.hiv2.domain.homebase.persistence.entity

import team.msg.hiv2.domain.homebase.persistence.entity.id.HomeBaseId
import javax.persistence.Column
import team.msg.hiv2.global.entity.BaseIdEntity
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

@Entity
@Table(name = "home_base")
@IdClass(HomeBaseId::class)
class HomeBaseJpaEntity(

    override val id: Long,

    @Id
    val floor: Int,

    @Id
    val period: Int,

    @Id
    val homeBaseNumber: Int,

    @Column(name = "max_capacity", nullable = false)
    val maxCapacity: Int

) : BaseIdEntity(id)