package team.msg.hiv2.global.entity

import java.time.LocalDateTime
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseTimeEntity(
    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    open val createdAt: LocalDateTime = LocalDateTime.now()
)