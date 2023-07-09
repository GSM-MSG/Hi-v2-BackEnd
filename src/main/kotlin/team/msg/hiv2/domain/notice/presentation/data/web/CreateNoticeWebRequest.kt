package team.msg.hiv2.domain.notice.presentation.data.web

import javax.validation.constraints.NotBlank

data class CreateNoticeWebRequest(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val content: String
)
