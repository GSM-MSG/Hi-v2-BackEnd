package team.msg.hiv2.domain.reservation.persistence.entity

import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity
import team.msg.hiv2.global.entity.BaseUuidEntity
import java.util.*
import javax.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "reservation")
class ReservationJpaEntity(

    override val id: UUID,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_base_id")
    val homeBase: HomeBaseJpaEntity,

    @Column(columnDefinition = "VARCHAR(501)", nullable = false)
    val reason: String,

    @Column(name = "check_status", nullable = false)
    var checkStatus: Boolean = false,

    @JoinColumn(name = "reservation_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    @ElementCollection
    @CollectionTable(name = "users", joinColumns = [JoinColumn(name = "reservation_id")])
    val userIds: MutableList<UUID>

) : BaseUuidEntity(id)