package team.msg.hiv2.aspect

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.notice.presentation.data.request.UpdateNoticeRequest
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UserRole
import java.util.*

@Component
@Aspect
class NoticeVerifyWriterAspect(
    private val userService: UserService,
    private val noticeService: NoticeService,
    private val userValidator: UserValidator
) {
    @Pointcut("execution(* team.msg.hiv2.domain.notice.application.usecase.UpdateNoticeUseCase.execute(..))" +
            "&& args(id, updateNoticeRequest) && within(team.msg.hiv2.domain.notice.application.usecase.UpdateNoticeUseCase)")
    private fun updateNoticeUseCasePointcut(id: UUID, updateNoticeRequest: UpdateNoticeRequest) {}

    @Pointcut("execution(* team.msg.hiv2.domain.notice.application.usecase.DeleteNoticeUseCase.execute(..))" +
            "&& args(id) && within(team.msg.hiv2.domain.notice.application.usecase.DeleteNoticeUseCase)")
    private fun deleteNoticeUseCasePointcut(id: UUID) {}

    @Before("updateNoticeUseCasePointcut(id, updateNoticeRequest)")
    private fun checkWriter(id: UUID, updateNoticeRequest: UpdateNoticeRequest) {
        val user = userService.queryCurrentUser()

        if (user.role == UserRole.ROLE_TEACHER || user.role == UserRole.ROLE_ADMIN) {
            val notice = noticeService.queryNoticeById(id)
            userValidator.checkUserAndWriter(user.id, notice.userId)
        }
    }

    @Before("deleteNoticeUseCasePointcut(id)")
    private fun checkWriter(id: UUID) {
        val user = userService.queryCurrentUser()

        if (user.role == UserRole.ROLE_TEACHER || user.role == UserRole.ROLE_ADMIN) {
            val notice = noticeService.queryNoticeById(id)
            userValidator.checkUserAndWriter(user.id, notice.userId)
        }
    }
}