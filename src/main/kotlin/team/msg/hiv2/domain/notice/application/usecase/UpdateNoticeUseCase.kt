package team.msg.hiv2.domain.notice.application.usecase

import team.msg.hiv2.domain.notice.application.spi.NoticePort
import team.msg.hiv2.domain.notice.exception.NoticeNotFoundException
import team.msg.hiv2.domain.notice.presentation.data.request.UpdateNoticeRequest
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class UpdateNoticeUseCase(
    private val noticePort: NoticePort,
    private val userValidator: UserValidator,
    private val queryUserPort: QueryUserPort
) {
    fun execute(id: UUID, updateNoticeRequest: UpdateNoticeRequest) {
        val notice = noticePort.queryNoticeById(id)
            ?: throw NoticeNotFoundException()
        val user = queryUserPort.queryCurrentUser()

        if(user.roles.firstOrNull() == UserRole.ROLE_TEACHER) {
            userValidator.checkUserAndWriter(user.id, notice.userId)
        }

        noticePort.save(notice.copy(title = updateNoticeRequest.title, content = updateNoticeRequest.content))
    }
}