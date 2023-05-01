package team.msg.hiv2.global.annotation.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Component
@Transactional(rollbackFor = [Exception::class])
annotation class Adapter()