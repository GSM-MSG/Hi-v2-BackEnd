package team.msg.hiv2.domain.notice.application.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.notice.presentation.data.request.UpdateNoticeRequest
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.HiTest
import java.time.LocalDateTime
import java.util.*

@HiTest
class UpdateNoticeUseCaseTest {
    @Mock
    private lateinit var noticeService: NoticeService

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var userValidator: UserValidator

    private lateinit var updateNoticeUseCase: UpdateNoticeUseCase

    private val noticeId = UUID.randomUUID()
    private val userId = UUID.randomUUID()
    private val title = "title_test"
    private val content = "content_test"

    private val noticeStub: Notice by lazy {
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

    private val updateNoticeStub: UpdateNoticeRequest by lazy {
        UpdateNoticeRequest(
            title = title,
            content = content
        )
    }

    @BeforeEach
    fun setUp() {
        updateNoticeUseCase = UpdateNoticeUseCase(
            noticeService, userValidator, userService
        )
    }

    @Test
    fun `공지사항 수정 성공`() {

        // given
        given(noticeService.queryNoticeById(noticeId))
            .willReturn(noticeStub)

        given(userService.queryCurrentUser())
            .willReturn(userStub)

        given(noticeService.save(any()))
            .willReturn(noticeStub)

        // when & then
        assertDoesNotThrow {
            updateNoticeUseCase.execute(noticeId, updateNoticeStub)
        }
    }
}