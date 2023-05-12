package team.msg.hiv2.domain.user.persistence.entity

import team.msg.hiv2.domain.reservation.persistence.entity.ReservationJpaEntity
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

    @Column(nullable = true)
    val grade: Int,

    @Column(nullable = true)
    val classNum: Int,

    @Column(nullable = true)
    val number: Int,

    @Column(columnDefinition = "TEXT", nullable = false)
    var profileImageUrl: String = "",

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    val reservation: ReservationJpaEntity? = null,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = [JoinColumn(name = "user_id")])
    var roles: MutableList<UserRole> = mutableListOf()

) : BaseUuidEntity(id)