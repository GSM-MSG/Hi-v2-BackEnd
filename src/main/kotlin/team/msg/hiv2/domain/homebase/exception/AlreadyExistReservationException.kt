package team.msg.hiv2.domain.homebase.exception

import team.msg.hiv2.global.error.ErrorCode
import team.msg.hiv2.global.error.exception.HiException

class AlreadyExistReservationException : HiException(ErrorCode.ALREADY_EXIST_RESERVATION)