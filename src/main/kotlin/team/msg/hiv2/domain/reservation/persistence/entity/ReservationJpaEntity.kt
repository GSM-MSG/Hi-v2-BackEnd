package team.msg.hiv2.domain.reservation.persistence.entity

import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import team.msg.hiv2.domain.homebase.persistence.entity.HomeBaseJpaEntity
import team.msg.hiv2.global.entity.BaseUuidEntity
import java.util.*

@Entity
@Table(name = "reservation")
class ReservationJpaEntity(

    override val id: UUID,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_base_id")
    val homeBase: HomeBaseJpaEntity,

    @Column(columnDefinition = "VARCHAR(600)", nullable = false)
    val reason: String,

    @Column(name = "check_status", nullable = false)
    var checkStatus: Boolean = true,

    @JoinColumn(name = "reservation_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(columnDefinition = "BINARY(16)", name = "user_id")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
        name = "users",
        joinColumns = [JoinColumn(name = "reservation_id")]
    )
    val userIds: MutableList<UUID>

) : BaseUuidEntity(id)