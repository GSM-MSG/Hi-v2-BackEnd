package team.msg.hiv2.domain.notice.persistence.entity

import team.msg.hiv2.domain.user.persistence.entity.UserJpaEntity
import team.msg.hiv2.global.entity.BaseUuidEntity
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "notice")
class NoticeJpaEntity(
    override val id: UUID,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val content: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity,

    override val createdAt: LocalDateTime

) : BaseUuidEntity(id)