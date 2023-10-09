package team.msg.hiv2.domain.homebase.persistence.entity

import team.msg.hiv2.global.entity.BaseIdEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "home_base")
class HomeBaseJpaEntity(

    override val id: Long,

    val floor: Int,

    val period: Int

) : BaseIdEntity(id)