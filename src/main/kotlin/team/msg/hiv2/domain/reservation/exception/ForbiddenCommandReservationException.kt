package team.msg.hiv2.domain.reservation.exception

import team.msg.hiv2.global.error.ErrorCode
import team.msg.hiv2.global.error.exception.HiException

class ForbiddenCommandReservationException : HiException(ErrorCode.FORBIDDEN_COMMAND_RESERVATION)