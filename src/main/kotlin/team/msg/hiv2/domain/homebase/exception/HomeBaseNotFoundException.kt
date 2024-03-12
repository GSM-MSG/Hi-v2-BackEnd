package team.msg.hiv2.domain.homebase.exception

import team.msg.hiv2.global.error.ErrorCode
import team.msg.hiv2.global.error.exception.HiException

class HomeBaseNotFoundException : HiException(ErrorCode.HOME_BASE_NOT_FOUND)