package team.msg.hiv2.domain.notice.application.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mock
import org.mockito.kotlin.given
import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.HiTest
import java.time.LocalDateTime
import java.util.*

@HiTest
class DeleteNoticeUseCaseTest {

    @Mock
    private lateinit var noticeService: NoticeService

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var userValidator: UserValidator

    private lateinit var deleteNoticeUseCase: DeleteNoticeUseCase

    private val noticeId = UUID.randomUUID()
    private val userId = UUID.randomUUID()
    private val title = "title_test"
    private val content = "content_test"

    private val requestStub: Notice by lazy {
        Notice(
            id = noticeId,
            title = title,
            content = content,
            userId = userId,
            createdAt = LocalDateTime.MAX
        )
    }

    private val userStub: User by lazy {
        User(
            id = userId,
            email = "test@email",
            name = "test",
            grade = 2,
            classNum = 3,
            number = 6,
            profileImageUrl = "profileImageUrl",
            roles = mutableListOf(UserRole.ROLE_ADMIN),
            reservationId = null,
            useStatus = UseStatus.AVAILABLE
        )
    }

    @BeforeEach
    fun setUp() {
        deleteNoticeUseCase = DeleteNoticeUseCase(
            noticeService, userService, userValidator
        )
    }

    @Test
    fun `공지사항 삭제 성공`() {

        // given
        given(userService.queryCurrentUser())
            .willReturn(userStub)

        given(noticeService.queryNoticeById(noticeId))
            .willReturn(requestStub)

        // when & then
        assertDoesNotThrow {
            deleteNoticeUseCase.execute(noticeId)
        }
    }
}