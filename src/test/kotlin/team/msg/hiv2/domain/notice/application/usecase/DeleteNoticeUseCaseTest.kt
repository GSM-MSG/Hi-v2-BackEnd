package team.msg.hiv2.domain.notice.application.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mock
import org.mockito.kotlin.given
import team.msg.hiv2.domain.notice.application.service.NoticeService
import team.msg.hiv2.domain.notice.domain.Notice
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

    @BeforeEach
    fun setUp() {
        deleteNoticeUseCase = DeleteNoticeUseCase(
            noticeService
        )
    }

    @Test
    fun `공지사항 삭제 성공`() {

        // given
        given(noticeService.queryNoticeById(noticeId))
            .willReturn(requestStub)

        // when & then
        assertDoesNotThrow {
            deleteNoticeUseCase.execute(noticeId)
        }
    }
}