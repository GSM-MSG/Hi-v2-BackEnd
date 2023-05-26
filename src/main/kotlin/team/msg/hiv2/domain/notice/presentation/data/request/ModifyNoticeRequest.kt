package team.msg.hiv2.domain.notice.presentation.data.request

import javax.validation.constraints.NotEmpty

data class ModifyNoticeRequest(
    @field:NotEmpty
    val title: String,
    @field:NotEmpty
    val content: String
)