package team.msg.hiv2.domain.reservation.exception

import team.msg.hiv2.global.error.ErrorCode
import team.msg.hiv2.global.error.exception.HiException

class ForbiddenExitReservationException : HiException(ErrorCode.FORBIDDEN_EXIT_RESERVATION)