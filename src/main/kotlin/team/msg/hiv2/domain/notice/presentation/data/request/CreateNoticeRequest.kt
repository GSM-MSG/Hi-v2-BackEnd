package team.msg.hiv2.domain.notice.presentation.data.request

import javax.validation.constraints.NotBlank

data class CreateNoticeRequest(
    val title: String,
    val content: String
)