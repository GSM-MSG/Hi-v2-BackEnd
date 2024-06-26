package team.msg.hiv2.domain.user.application.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.given
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.presentation.data.response.UserRoleResponse
import team.msg.hiv2.global.annotation.HiTest
import java.util.*

@HiTest
internal class QueryMyRoleUseCaseTest {

    @Mock
    private lateinit var userService: UserService

    private lateinit var queryMyRoleUseCase: QueryMyRoleUseCase

    private val userId = UUID.randomUUID()
    private val userRole = UserRole.ROLE_STUDENT

    private val userStub: User by lazy {
        User(
            id = userId,
            email = "test@email",
            name = "test",
            schoolNumber = "2306",
            profileImageUrl = "profileImageUrl",
            role = userRole,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val responseStub by lazy {
        UserRoleResponse.of(userId, userStub.role)
    }

    @BeforeEach
    fun setUp() {
        queryMyRoleUseCase = QueryMyRoleUseCase(
            userService
        )
    }

    @Test
    fun `내 역할 조회 성공`() {

        // given
        given(userService.queryCurrentUser())
            .willReturn(userStub)

        // when
        val response = queryMyRoleUseCase.execute()

        // then
        assertThat(response).isEqualTo(responseStub)
    }
}