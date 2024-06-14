package team.msg.hiv2.domain.notice.presentation.data.web

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class UpdateNoticeWebRequest(
    @field:NotEmpty
    @field:Size(max = 20)
    val title: String,
    @field:NotEmpty
    @field:Size(max = 200)
    val content: String
)
