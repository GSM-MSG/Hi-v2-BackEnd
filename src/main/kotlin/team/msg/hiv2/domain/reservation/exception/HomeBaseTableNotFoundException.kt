package team.msg.hiv2.domain.reservation.exception

import team.msg.hiv2.global.error.ErrorCode
import team.msg.hiv2.global.error.exception.HiException

class HomeBaseTableNotFoundException : HiException(ErrorCode.HOME_BASE_TABLE_NOT_FOUND)