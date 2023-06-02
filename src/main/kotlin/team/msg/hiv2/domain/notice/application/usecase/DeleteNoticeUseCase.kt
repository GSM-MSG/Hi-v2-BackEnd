package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.spi.NoticePort
import team.msg.hiv2.domain.notice.exception.ForbiddenCommandNotice
import team.msg.hiv2.domain.notice.exception.NoticeNotFoundException
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class DeleteNoticeUseCase(
    private val noticePort: NoticePort,
    private val queryUserPort: QueryUserPort,
    private val userValidator: UserValidator
) {
    fun execute(id: UUID) {
        val user = queryUserPort.queryCurrentUser()
        val notice = noticePort.queryNoticeById(id)
            ?: throw NoticeNotFoundException()

        if(user.roles.firstOrNull() == UserRole.ROLE_TEACHER) {
            userValidator.checkUserAndWriter(user.id, notice.userId)
        }

        noticePort.delete(notice)
    }
}