package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class DeleteNoticeUseCase(
    private val noticeService: NoticeService,
    private val userService: UserService,
    private val userValidator: UserValidator
) {
    fun execute(id: UUID) {
        val user = userService.queryCurrentUser()
        val notice = noticeService.queryNoticeById(id)

        if(user.role == UserRole.ROLE_TEACHER)
            userValidator.checkUserAndWriter(user.id, notice.userId)

        noticeService.delete(notice)
    }
}