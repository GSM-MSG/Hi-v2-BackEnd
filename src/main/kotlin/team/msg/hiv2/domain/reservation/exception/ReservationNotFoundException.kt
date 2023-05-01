package team.msg.hiv2.domain.reservation.exception

import team.msg.hiv2.global.error.ErrorCode
import team.msg.hiv2.global.error.exception.HiException

class ReservationNotFoundException : HiException(ErrorCode.RESERVATION_NOT_FOUND)