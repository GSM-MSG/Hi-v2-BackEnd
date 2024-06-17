package team.msg.hiv2.global.entity

import jakarta.persistence.*
import java.util.*

@MappedSuperclass
abstract class BaseUuidEntity(

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    open val id: UUID
) : BaseTimeEntity()