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
            grade = 2,
            classNum = 3,
            number = 6,
            profileImageUrl = "profileImageUrl",
            roles = mutableListOf(UserRole.ROLE_STUDENT),
            reservationId = null,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val requestStub = UseStatus.UNAVAILABLE

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
            updateUserUseStatusUserService.execute(userId ,requestStub)
        }
    }

}