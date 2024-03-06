package team.msg.hiv2.domain.auth.application.usecase

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.given
import team.msg.hiv2.domain.auth.application.service.RefreshTokenService
import team.msg.hiv2.domain.auth.domain.RefreshToken
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.HiTest
import java.util.*

@HiTest
class LogoutUseCaseTest {

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var refreshTokenService: RefreshTokenService

    private lateinit var logoutUseCase: LogoutUseCase

    private val requestToken = "request_token"

    private val userStub by lazy {
        User(
            id = UUID.randomUUID(),
            email = "test_email",
            name = "name",
            grade = 2,
            classNum = 4,
            number = 6,
            profileImageUrl = "profile_image_url",
            role = UserRole.ROLE_STUDENT,
            reservationId = null,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val refreshTokenStub by lazy {
        RefreshToken(
            refreshToken = requestToken,
            userId = userStub.id,
            expiredAt = 100800
        )
    }

    @BeforeEach
    fun setUp() {
        logoutUseCase = LogoutUseCase(
            userService, refreshTokenService
        )
    }

    @Test
    fun `로그아웃 성공`() {
        // given
        given(userService.queryCurrentUser())
            .willReturn(userStub)

        given(refreshTokenService.queryByRefreshToken(requestToken))
            .willReturn(refreshTokenStub)

        // when & then
        assertDoesNotThrow {
            logoutUseCase.execute(requestToken)
        }
    }

}