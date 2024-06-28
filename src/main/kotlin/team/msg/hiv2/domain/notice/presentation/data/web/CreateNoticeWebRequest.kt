package team.msg.hiv2.domain.notice.presentation.data.web

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateNoticeWebRequest(
    @field:NotBlank
    @field:Size(max = 20)
    val title: String,
    @field:NotBlank
    @field:Size(max = 200)
    val content: String
)
