package team.msg.hiv2.global.annotation.lock

import java.util.concurrent.TimeUnit
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FUNCTION

@Target(FUNCTION)
@Retention(RUNTIME)
annotation class DistributedLock(

    val key: String,

    val timeUnit: TimeUnit = TimeUnit.SECONDS,

    val waitTime: Long = 5L,

    val leaseTime: Long = 3L

)