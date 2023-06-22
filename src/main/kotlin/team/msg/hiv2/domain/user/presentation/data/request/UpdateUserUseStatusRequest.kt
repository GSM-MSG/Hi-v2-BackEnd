package team.msg.hiv2.domain.user.presentation.data.request

import com.fasterxml.jackson.annotation.JsonCreator
import team.msg.hiv2.domain.user.domain.constant.UseStatus

class UpdateUserUseStatusRequest(
    @get:JsonCreator
    val status: UseStatus
)