package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeResponse
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryAllNoticeUseCase(
    private val noticeService: NoticeService,
    private val userService: UserService
) {
    fun execute(): List<NoticeResponse> {
        val notices = noticeService.queryAllNotice()

        return notices.map {
            val user = userService.queryUserById(it.userId)
            NoticeResponse.of(it, UserResponse.of(user))
        }
    }
}