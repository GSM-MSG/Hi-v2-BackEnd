package team.msg.hiv2.domain.notice.application.usecase

import org.apache.catalina.security.SecurityUtil
import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeDetailsResponse
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.presentation.data.response.NoticeUserResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase
import team.msg.hiv2.global.security.spi.SecurityPort
import java.util.UUID

@ReadOnlyUseCase
class QueryNoticeDetailsUseCase(
    private val noticeService: NoticeService,
    private val userService: UserService,
    private val securityPort: SecurityPort
) {
    fun execute(id: UUID): NoticeDetailsResponse {
        val currentUserId = securityPort.queryCurrentUserId()

        val notice = noticeService.queryNoticeById(id)
        val user = userService.queryUserById(notice.userId)

        return NoticeDetailsResponse.of(notice, UserResponse.of(user, notice.userId == currentUserId))
    }
}