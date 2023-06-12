package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.service.CommandNoticeService
import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.notice.application.spi.NoticePort
import team.msg.hiv2.domain.notice.exception.NoticeNotFoundException
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.usecase.UseCase
import team.msg.hiv2.global.error.exception.InvalidRoleException
import java.util.UUID

@UseCase
class DeleteNoticeUseCase(
    private val noticeService: NoticeService,
    private val queryUserService: QueryUserService,
    private val userValidator: UserValidator
) {
    fun execute(id: UUID) {
        val user = queryUserService.queryCurrentUser()
        val notice = noticeService.queryNoticeById(id)
        val role = user.roles.firstOrNull() ?: throw InvalidRoleException()

        if(role == UserRole.ROLE_TEACHER)
            userValidator.checkUserAndWriter(user.id, notice.userId)

        noticeService.delete(notice)
    }
}