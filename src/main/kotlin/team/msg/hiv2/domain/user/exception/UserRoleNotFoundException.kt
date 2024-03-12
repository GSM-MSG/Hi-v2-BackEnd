package team.msg.hiv2.domain.user.exception

import team.msg.hiv2.global.error.ErrorCode
import team.msg.hiv2.global.error.exception.HiException

class UserRoleNotFoundException : HiException(ErrorCode.USER_ROLE_NOT_FOUND)