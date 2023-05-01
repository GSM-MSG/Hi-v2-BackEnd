package team.msg.hiv2.domain.reservation.persistence.entity

import org.hibernate.annotations.Where
import team.msg.hiv2.domain.reservation.domain.constant.CheckStatus
import team.msg.hiv2.domain.user.persistence.entity.UserJpaEntity
import team.msg.hiv2.global.entity.BaseUuidEntity
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "home_base_table")
@Where(clause = "deleted_at is null")
class HomeBaseTableJpaEntity(

    override val id: UUID,

    @OneToOne
    @JoinColumn(name = "representative_id")
    val representative: UserJpaEntity,

    @Column(nullable = false)
    val reason: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "check_status", nullable = false)
    val checkStatus: CheckStatus,

    @Column(columnDefinition = "DATETIME")
    val deletedAt: LocalDateTime?

) : BaseUuidEntity(id)