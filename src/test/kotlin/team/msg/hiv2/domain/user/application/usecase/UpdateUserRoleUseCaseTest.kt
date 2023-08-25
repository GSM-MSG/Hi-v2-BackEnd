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
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserRoleWebRequest
import team.msg.hiv2.global.annotation.HiTest
import java.util.*

@HiTest
internal class UpdateUserRoleUseCaseTest {

    @Mock
    private lateinit var userService: UserService

    private lateinit var updateUserRoleUseCase: UpdateUserRoleUseCase

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

    private val requestStub by lazy {
        UpdateUserRoleWebRequest(
            role = UserRole.ROLE_ADMIN
        )
    }

    @BeforeEach
    fun setUp() {
        updateUserRoleUseCase = UpdateUserRoleUseCase(
            userService
        )
    }

    @Test
    fun `유저 역할 변경 성공`() {

        // given
        given(userService.queryUserById(userId))
            .willReturn(userStub)

        // when & then
        assertDoesNotThrow {
            updateUserRoleUseCase.execute(userId, requestStub)
        }
    }
}