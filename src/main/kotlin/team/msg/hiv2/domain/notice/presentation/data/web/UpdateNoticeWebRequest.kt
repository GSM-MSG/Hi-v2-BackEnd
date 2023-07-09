package team.msg.hiv2.domain.notice.presentation.data.web

import javax.validation.constraints.NotEmpty

data class UpdateNoticeWebRequest(
    @field:NotEmpty
    val title: String,
    @field:NotEmpty
    val content: String
)
