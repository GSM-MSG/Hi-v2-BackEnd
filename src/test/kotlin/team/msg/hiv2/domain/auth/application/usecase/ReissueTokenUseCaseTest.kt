package team.msg.hiv2.domain.auth.application.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.given
import team.msg.hiv2.domain.auth.application.service.RefreshTokenService
import team.msg.hiv2.domain.auth.domain.RefreshToken
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.HiTest
import team.msg.hiv2.global.security.spi.GenerateJwtPort
import team.msg.hiv2.global.security.spi.JwtParserPort
import java.time.LocalDateTime
import java.util.*

@HiTest
class ReissueTokenUseCaseTest {

    @Mock
    private lateinit var refreshTokenService: RefreshTokenService

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var generateJwtPort: GenerateJwtPort

    @Mock
    private lateinit var jwtParserPort: JwtParserPort

    private lateinit var reissueTokenUseCase: ReissueTokenUseCase

    private val userStub by lazy {
        User(
            id = UUID.randomUUID(),
            email = "test_email",
            name = "test_name",
            grade = 2,
            classNum = 4,
            number = 6,
            profileImageUrl = "profile_image_url",
            role = mutableListOf(UserRole.ROLE_STUDENT),
            reservationId = null,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val refreshTokenStub by lazy {
        RefreshToken(
            refreshToken = "refresh_token",
            userId = userStub.id,
            expiredAt = 100800
        )
    }

    private val requestToken = "refresh_token"

    private val responseStub by lazy {
        TokenResponse(
            accessToken = "access_token",
            refreshToken = "refresh_token",
            accessExpiredAt = LocalDateTime.now(),
            refreshExpiredAt = LocalDateTime.now()
        )
    }

    @BeforeEach
    fun setUp() {
        reissueTokenUseCase = ReissueTokenUseCase(
            refreshTokenService, userService, generateJwtPort, jwtParserPort
        )
    }

    @Test
    fun `토큰 재발급 성공`() {
        // given
        given(jwtParserPort.parseRefreshToken(requestToken))
            .willReturn(refreshTokenStub.refreshToken)

        given(refreshTokenService.queryByRefreshToken(requestToken))
            .willReturn(refreshTokenStub)

        given(userService.queryUserById(refreshTokenStub.userId))
            .willReturn(userStub)

        given(generateJwtPort.generate(userStub.id, userStub.role))
            .willReturn(responseStub)

        // when
        val result = reissueTokenUseCase.execute(requestToken)

        // then
        assertThat(result).isEqualTo(responseStub)
    }

}