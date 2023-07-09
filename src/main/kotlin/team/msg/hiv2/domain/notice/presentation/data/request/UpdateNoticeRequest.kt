package team.msg.hiv2.domain.notice.presentation.data.request

import javax.validation.constraints.NotEmpty

data class UpdateNoticeRequest(
    val title: String,
    val content: String
)