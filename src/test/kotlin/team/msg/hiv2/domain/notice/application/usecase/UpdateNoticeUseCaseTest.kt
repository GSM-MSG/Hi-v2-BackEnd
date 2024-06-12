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

    private val requestStub: UpdateNoticeRequest by lazy {
        UpdateNoticeRequest(
            title = title,
            content = content
        )
    }

    @BeforeEach
    fun setUp() {
        updateNoticeUseCase = UpdateNoticeUseCase(
            noticeService
        )
    }

    @Test
    fun `공지사항 수정 성공`() {

        // given
        given(noticeService.queryNoticeById(noticeId))
            .willReturn(noticeStub)

        given(noticeService.save(any()))
            .willReturn(noticeStub)

        // when & then
        assertDoesNotThrow {
            updateNoticeUseCase.execute(noticeId, requestStub)
        }
    }
}