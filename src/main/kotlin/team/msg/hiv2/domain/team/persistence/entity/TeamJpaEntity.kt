package team.msg.hiv2.domain.team.persistence.entity

import team.msg.hiv2.global.entity.BaseUuidEntity
import java.util.*
import javax.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "team")
class TeamJpaEntity(

    override val id: UUID,

    @JoinColumn(name = "team_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    @ElementCollection
    @CollectionTable(name = "users", joinColumns = [JoinColumn(name = "team_id")])
    val userIds: MutableList<UUID>

) : BaseUuidEntity(id)