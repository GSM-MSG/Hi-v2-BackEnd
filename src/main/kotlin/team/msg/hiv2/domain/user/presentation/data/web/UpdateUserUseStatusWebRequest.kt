package team.msg.hiv2.domain.user.presentation.data.web

import team.msg.hiv2.domain.user.domain.constant.UseStatus
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

data class UpdateUserUseStatusWebRequest(
    @field:Enumerated(EnumType.STRING)
    val status: UseStatus
)
