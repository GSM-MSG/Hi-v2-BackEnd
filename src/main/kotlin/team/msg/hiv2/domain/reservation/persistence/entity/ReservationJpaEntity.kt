package team.msg.hiv2.domain.reservation.persistence.entity

import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity
import team.msg.hiv2.domain.team.persistence.entity.TeamJpaEntity
import team.msg.hiv2.global.entity.BaseUuidEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "reservation", uniqueConstraints = [UniqueConstraint(columnNames = ["home_base_id", "reservation_number"])])
class ReservationJpaEntity(

    override val id: UUID,

    @ManyToOne
    @JoinColumn(name = "home_base_id")
    val homeBase: HomeBaseJpaEntity,

    @ManyToOne
    @JoinColumn(name = "team_id")
    val team: TeamJpaEntity,

    @Column(nullable = false)
    val reason: String,

    @Column(name = "check_status", nullable = false)
    var checkStatus: Boolean = false,

    @Column(name = "reservation_number", nullable = false)
    val reservationNumber: Int

) : BaseUuidEntity(id)