package team.msg.hiv2.domain.homebase.exception

import team.msg.hiv2.global.error.ErrorCode
import team.msg.hiv2.global.error.exception.HiException

class AlreadyReservedAtSamePeriodException : HiException(ErrorCode.ALREADY_RESERVED_AT_SAME_PERIOD)