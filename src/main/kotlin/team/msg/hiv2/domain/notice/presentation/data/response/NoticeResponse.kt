package team.msg.hiv2.domain.notice.presentation.data.response

import team.msg.hiv2.domain.user.presentation.data.response.UserResponse

data class NoticeResponse(
    val title: String,
    val content: String,
    val user: UserResponse
)