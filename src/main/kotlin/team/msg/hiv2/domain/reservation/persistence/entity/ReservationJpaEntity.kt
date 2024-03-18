package team.msg.hiv2.domain.reservation.persistence.entity

import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity
import team.msg.hiv2.domain.team.persistence.entity.TeamJpaEntity
import team.msg.hiv2.global.entity.BaseUuidEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "reservation")
class ReservationJpaEntity(

    override val id: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_base_id")
    val homeBase: HomeBaseJpaEntity,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    val team: TeamJpaEntity,

    @Column(nullable = false)
    val reason: String,

    @Column(name = "check_status", nullable = false)
    var checkStatus: Boolean = false

) : BaseUuidEntity(id)