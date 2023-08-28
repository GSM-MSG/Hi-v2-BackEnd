package team.msg.hiv2.aspect

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.notice.presentation.data.request.UpdateNoticeRequest
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UserRole
import java.util.*

@Component
@Aspect
class NoticeAspect(
    private val userService: UserService,
    private val noticeService: NoticeService,
    private val userValidator: UserValidator
) {

    private val log by lazy { LoggerFactory.getLogger(this.javaClass.simpleName) }

    @Pointcut("execution(* team.msg.hiv2.domain.notice.application.usecase.UpdateNoticeUseCase.execute(..))" +
            "&& args(id, updateNoticeRequest) && within(team.msg.hiv2.domain.notice.application.usecase.UpdateNoticeUseCase)")
    private fun updateNoticeUseCasePointcut(id: UUID, updateNoticeRequest: UpdateNoticeRequest) {}

    @Before("updateNoticeUseCasePointcut(id, updateNoticeRequest)")
    private fun checkWriter(id: UUID, updateNoticeRequest: UpdateNoticeRequest) {
        val user = userService.queryCurrentUser()
        val notice = noticeService.queryNoticeById(id)

        if (user.roles.firstOrNull() == UserRole.ROLE_TEACHER)
            userValidator.checkUserAndWriter(user.id, notice.userId)
    }
}