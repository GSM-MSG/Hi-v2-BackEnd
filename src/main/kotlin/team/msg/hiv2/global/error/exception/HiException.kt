package team.msg.hiv2.global.error.exception

import team.msg.hiv2.global.error.ErrorCode

abstract class HiException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)