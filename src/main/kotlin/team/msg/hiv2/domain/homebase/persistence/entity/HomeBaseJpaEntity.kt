package team.msg.hiv2.domain.homebase.persistence.entity

import org.hibernate.annotations.Where
import team.msg.hiv2.global.entity.BaseUuidEntity
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "home_base")
@Where(clause = "deleted_at is null")
class HomeBaseJpaEntity(

    override val id: UUID,

    val floor: Int,

    val period: Int

) : BaseUuidEntity(id)