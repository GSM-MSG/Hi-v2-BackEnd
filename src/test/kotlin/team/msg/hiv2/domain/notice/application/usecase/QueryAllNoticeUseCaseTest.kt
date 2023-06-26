package team.msg.hiv2.domain.notice.application.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.given
import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeResponse
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.HiTest
import java.time.LocalDateTime
import java.util.*

@HiTest
class QueryAllNoticeUseCaseTest {

    @Mock
    private lateinit var noticeService: NoticeService

    @Mock
    private lateinit var userService: UserService

    private lateinit var queryAllNoticeUseCase: QueryAllNoticeUseCase

    private val noticeId = UUID.randomUUID()
    private val userId = UUID.randomUUID()
    private val title = "title_test"
    private val content = "content_test"

    private val noticeListStub = listOf(
        Notice(
            id = noticeId,
            title = title,
            content = content,
            userId = userId,
            createdAt = LocalDateTime.MAX
        )
    )

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

    private val responseStub = listOf(
        NoticeResponse(
            noticeId = noticeId,
            title = title,
            user = UserResponse(
                userId = userId,
                name = "test",
                grade = 2,
                classNum = 3,
                number = 6
            ),
            createdAt = LocalDateTime.MAX
        )
    )

    @BeforeEach
    fun setUp() {
        queryAllNoticeUseCase = QueryAllNoticeUseCase(
            noticeService, userService
        )
    }

    @Test
    fun `공지사항 전체 조회 성공`() {

        // given
        given(noticeService.queryAllNotice())
            .willReturn(noticeListStub)

        given(userService.queryUserById(userId))
            .willReturn(userStub)

        // when
        val result = queryAllNoticeUseCase.execute()

        // then
        assertEquals(result, responseStub)
    }
}