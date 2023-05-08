package team.msg.hiv2.domain.reservation.persistence.entity

import team.msg.hiv2.domain.user.persistence.entity.UserJpaEntity
import team.msg.hiv2.global.entity.BaseUuidEntity
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "reservation")
class ReservationJpaEntity(

    override val id: UUID,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity,

    @ManyToOne
    @JoinColumn(name = "home_base_table_id")
    val homeBaseTable: HomeBaseTableJpaEntity,

) : BaseUuidEntity(id)