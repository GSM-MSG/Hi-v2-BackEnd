package team.msg.hiv2.domain.user.application.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mock
import org.mockito.kotlin.given
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserUseStatusRequest
import team.msg.hiv2.global.annotation.HiTest
import java.util.*

@HiTest
internal class UpdateUserUseStatusUseCaseTest {

    @Mock
    private lateinit var userService: UserService

    private lateinit var updateUserUseStatusUserService: UpdateUserUseStatusUseCase

    private val userId = UUID.randomUUID()

    private val userStub: User by lazy {
        User(
            id = userId,
            email = "test@email",
            name = "test",
            schoolNumber = "2306",
            profileImageUrl = "profileImageUrl",
            role = UserRole.ROLE_STUDENT,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val requestStub by lazy {
        UpdateUserUseStatusRequest(
            status = UseStatus.UNAVAILABLE
        )
    }

    @BeforeEach
    fun setUp() {
        updateUserUseStatusUserService = UpdateUserUseStatusUseCase(
            userService
        )
    }

    @Test
    fun `학생 상태 변경 성공`() {

        // given
        given(userService.queryUserById(userId))
            .willReturn(userStub)

        // when & then
        assertDoesNotThrow {
            updateUserUseStatusUserService.execute(userId, requestStub)
        }
    }

}