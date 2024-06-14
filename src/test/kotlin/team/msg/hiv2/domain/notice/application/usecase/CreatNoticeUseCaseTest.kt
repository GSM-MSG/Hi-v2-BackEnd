package team.msg.hiv2.domain.notice.application.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.notice.presentation.data.request.CreateNoticeRequest
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.HiTest
import java.time.LocalDateTime
import java.util.UUID

@HiTest
internal class CreatNoticeUseCaseTest {

    @Mock
    private lateinit var noticeService: NoticeService

    @Mock
    private lateinit var userService: UserService

    private lateinit var createNoticeUseCase: CreateNoticeUseCase

    private val title = "title_test"
    private val content = "content_test"

    private val userId = UUID.randomUUID()

    private val userStub: User by lazy {
        User(
            id = userId,
            email = "test@email",
            name = "test2",
            schoolNumber = "2406",
            profileImageUrl = "profileImageUrl",
            role = UserRole.ROLE_ADMIN,
            useStatus = UseStatus.AVAILABLE
        )
    }


    private val requestStub: CreateNoticeRequest by lazy {
        CreateNoticeRequest(title, content)
    }

    @BeforeEach
    fun setUp() {
        createNoticeUseCase = CreateNoticeUseCase(
            userService,
            noticeService
        )
    }


    @Test
    fun `공지사항 작성 성공`() {

        // given
        val noticeStub = Notice(
            id = UUID.randomUUID(),
            title = requestStub.title,
            content = requestStub.content,
            userId = userId,
            createdAt = LocalDateTime.now()
        )

        given(userService.queryCurrentUser())
            .willReturn(userStub)

        given(noticeService.save(any()))
            .willReturn(noticeStub)

        // when & then
        assertDoesNotThrow {
            createNoticeUseCase.execute(requestStub)
        }
    }
}