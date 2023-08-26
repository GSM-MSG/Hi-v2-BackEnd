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

        return notices.mapIndexed { index, notice ->
            val user = userService.queryUserById(notice.userId)
            NoticeResponse.of(notice, UserResponse.of(user), index + 1L)
        }
    }
}