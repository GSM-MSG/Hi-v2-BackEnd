package team.msg.hiv2.domain.auth.application.usecase

import gauth.response.GAuthToken
import gauth.response.GAuthUserInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import team.msg.hiv2.domain.auth.presentation.data.request.GAuthSignInRequest
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.HiTest
import team.msg.hiv2.global.security.spi.GenerateJwtPort
import team.msg.hiv2.thirdparty.gauth.spi.GAuthPort
import java.time.LocalDateTime
import java.util.*

@HiTest
class GAuthSignInUseCaseTest{

    @Mock
    private lateinit var gAuthPort: GAuthPort

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var generateJwtPort: GenerateJwtPort

    private lateinit var gAuthSignInUseCase: GAuthSignInUseCase

    private val email = "test@gsm.hs.kr"
    private val name = "test"
    private val grade = 2
    private val classNum = 4
    private val num = 6
    private val profileImageUrl = "profile_image_url"
    private val role = UserRole.ROLE_STUDENT

    private val code = "test_gauth_code"


    private val saveUserStub: User by lazy {
        User(
            id = UUID.randomUUID(),
            email = email,
            name = name,
            schoolNumber = "2306",
            profileImageUrl = profileImageUrl,
            role = role,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val requestStub: GAuthSignInRequest by lazy {
        GAuthSignInRequest(code)
    }

    private val responseStub: TokenResponse by lazy {
        TokenResponse(
            accessToken = "access_token",
            refreshToken = "refreshToken",
            accessExpiredAt = LocalDateTime.now(),
            refreshExpiredAt = LocalDateTime.now()
        )
    }

    private val gAuthTokenStub: GAuthToken by lazy {
        GAuthToken(
            mapOf(
                "accessToken" to "gauth_access_token",
                "refreshToken" to "gauth_refresh_token"
            )
        )
    }

    private val gAuthUserInfoStub: GAuthUserInfo by lazy {
        GAuthUserInfo(
            mapOf(
                "email" to email,
                "name" to name,
                "grade" to grade,
                "classNum" to classNum,
                "num" to num,
                "gender" to "MALE",
                "profileUrl" to profileImageUrl,
                "role" to "ROLE_STUDENT"
            )
        )
    }

    @BeforeEach
    fun setUp(){
        MockitoAnnotations.openMocks(this)

        gAuthSignInUseCase = GAuthSignInUseCase(
            gAuthPort, userService, generateJwtPort
        )
    }

    @Test
    fun `회원가입 성공`() {

        // given
        given(gAuthPort.queryGAuthToken(requestStub.code))
            .willReturn(gAuthTokenStub)

        given(gAuthPort.queryGAuthUserInfo(gAuthTokenStub.accessToken))
            .willReturn(gAuthUserInfoStub)

        given(userService.queryUserRoleByEmail(any(), any()))
            .willReturn(role)

        given(userService.existsUserByEmail(any()))
            .willReturn(false)

        given(userService.createUser(any(), any()))
            .willReturn(saveUserStub)

        given(generateJwtPort.generate(saveUserStub.id, saveUserStub.role))
            .willReturn(responseStub)

        // when
        val result = gAuthSignInUseCase.execute(requestStub)

        // then
        assertThat(result).isEqualTo(responseStub)
    }
}