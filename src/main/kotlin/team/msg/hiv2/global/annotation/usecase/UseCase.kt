package team.msg.hiv2.global.annotation.usecase

import org.springframework.transaction.annotation.Transactional
import kotlin.annotation.AnnotationRetention.*
import kotlin.annotation.AnnotationTarget.*

@Retention(RUNTIME)
@Target(CLASS)
@Transactional(rollbackFor = [Exception::class])
annotation class UseCase
