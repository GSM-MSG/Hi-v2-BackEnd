package team.msg.hiv2.domain.homebase.exception

import team.msg.hiv2.global.error.ErrorCode
import team.msg.hiv2.global.error.exception.HiException

class TooManyUsersException : HiException(ErrorCode.TOO_MANY_USERS_EXCEPTION)