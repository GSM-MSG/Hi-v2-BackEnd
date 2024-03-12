package team.msg.hiv2.domain.auth.exception

import team.msg.hiv2.global.error.ErrorCode
import team.msg.hiv2.global.error.exception.HiException

class RefreshTokenNotFoundException : HiException(ErrorCode.REFRESH_TOKEN_NOT_FOUND)