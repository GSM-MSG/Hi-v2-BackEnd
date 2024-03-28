package team.msg.hiv2.domain.notice.persistence.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import team.msg.hiv2.domain.user.persistence.entity.UserJpaEntity
import team.msg.hiv2.global.entity.BaseUuidEntity
import java.util.*

@Entity
@Table(name = "notice")
class NoticeJpaEntity(
    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    val title: String,

    @Column(columnDefinition = "VARCHAR(300)", nullable = false)
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserJpaEntity

) : BaseUuidEntity(id)