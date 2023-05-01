package team.msg.hiv2.global.annotation.adapter

import org.springframework.stereotype.Component

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Component
annotation class ReadOnlyAdapter()
