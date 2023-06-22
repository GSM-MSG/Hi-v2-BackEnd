package team.msg.hiv2.domain.user.presentation.data.request

import team.msg.hiv2.domain.user.domain.constant.UseStatus
import javax.persistence.EnumType
import javax.persistence.Enumerated

class UpdateUserUseStatusRequest(
    @field:Enumerated(EnumType.STRING)
    val status: UseStatus
)