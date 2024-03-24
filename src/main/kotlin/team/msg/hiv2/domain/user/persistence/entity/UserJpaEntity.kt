package team.msg.hiv2.domain.user.persistence.entity

import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.entity.BaseUuidEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
class UserJpaEntity(

    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
    val email: String,

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    val name: String,

    @Column(nullable = false)
    val grade: Int,

    @Column(nullable = false)
    val classNum: Int,

    @Column(nullable = false)
    val number: Int,

    @Column(columnDefinition = "TEXT", nullable = false)
    var profileImageUrl: String = "",

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    var role: UserRole,

    @Enumerated(EnumType.STRING)
    @Column(name = "use_status", nullable = false)
    val useStatus: UseStatus

) : BaseUuidEntity(id)