package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.notice.presentation.data.request.UpdateNoticeRequest
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.usecase.UseCase
import team.msg.hiv2.global.error.exception.InvalidRoleException
import java.util.UUID

@UseCase
class UpdateNoticeUseCase(
    private val noticeService: NoticeService,
    private val userValidator: UserValidator,
    private val userService: UserService
) {
    fun execute(id: UUID, updateNoticeRequest: UpdateNoticeRequest) {
        val notice = noticeService.queryNoticeById(id)
        val user = userService.queryCurrentUser()
        val role = user.roles.firstOrNull() ?: throw InvalidRoleException()

        if(role == UserRole.ROLE_TEACHER)
            userValidator.checkUserAndWriter(user.id, notice.userId)

         noticeService.save(notice.copy(title = updateNoticeRequest.title, content = updateNoticeRequest.content))
    }
}