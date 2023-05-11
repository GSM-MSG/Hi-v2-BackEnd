package team.msg.hiv2.domain.notice.presentation.data.request

import javax.validation.constraints.NotBlank

data class CreateNoticeRequest(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val content: String,
)