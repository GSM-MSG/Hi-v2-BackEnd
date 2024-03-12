package team.msg.hiv2.domain.notice.exception

import team.msg.hiv2.global.error.ErrorCode
import team.msg.hiv2.global.error.exception.HiException

class ForbiddenCommandNoticeException : HiException(ErrorCode.FORBIDDEN_COMMAND_NOTICE)