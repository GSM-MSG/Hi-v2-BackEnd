package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.service.CommandNoticeService
import team.msg.hiv2.domain.notice.application.service.QueryNoticeService
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.usecase.UseCase
import team.msg.hiv2.global.error.exception.InvalidRoleException
import java.util.UUID

@UseCase
class DeleteNoticeUseCase(
    private val commandNoticeService: CommandNoticeService,
    private val queryNoticeService: QueryNoticeService,
    private val queryUserService: QueryUserService,
    private val userValidator: UserValidator
) {
    fun execute(id: UUID) {
        val user = queryUserService.queryCurrentUser()
        val notice = queryNoticeService.queryNoticeById(id)
        val role = user.roles.firstOrNull() ?: throw InvalidRoleException()

        if(role == UserRole.ROLE_TEACHER)
            userValidator.checkUserAndWriter(user.id, notice.userId)

        commandNoticeService.delete(notice)
    }
}