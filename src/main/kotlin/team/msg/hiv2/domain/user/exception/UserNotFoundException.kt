package team.msg.hiv2.domain.user.exception

import team.msg.hiv2.global.error.ErrorCode
import team.msg.hiv2.global.error.exception.HiException

class UserNotFoundException : HiException(ErrorCode.USER_NOT_FOUND)