package team.msg.hiv2.global.error.exception

import team.msg.hiv2.global.error.ErrorCode

open class HiException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)