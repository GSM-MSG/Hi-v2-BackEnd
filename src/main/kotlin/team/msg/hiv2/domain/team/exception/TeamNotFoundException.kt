package team.msg.hiv2.domain.team.exception

import team.msg.hiv2.global.error.ErrorCode
import team.msg.hiv2.global.error.exception.HiException

class TeamNotFoundException : HiException(ErrorCode.TEAM_NOT_FOUND)