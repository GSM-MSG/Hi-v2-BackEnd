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
import team.msg.hiv2.domain.user.presentation.data.response.AllUsersResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.HiTest
import java.util.UUID

@HiTest
internal class QueryAllUsersByUserRoleUseCaseTest {

    @Mock
    private lateinit var userService: UserService

    private lateinit var queryAllUsersByUserRoleUseCase: QueryAllUsersByUserRoleUseCase

    private val userId1 = UUID.randomUUID()
    private val userId2 = UUID.randomUUID()
    private val userRole = UserRole.ROLE_STUDENT

    private val userStub1: User by lazy {
        User(
            id = userId1,
            email = "test1@email",
            name = "test1",
            grade = 2,
            classNum = 3,
            number = 6,
            profileImageUrl = "profileImageUrl",
            role = userRole,
            reservationId = null,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val userStub2: User by lazy {
        User(
            id = userId2,
            email = "test2@email",
            name = "test2",
            grade = 2,
            classNum = 3,
            number = 7,
            profileImageUrl = "profileImageUrl",
            role = userRole,
            reservationId = null,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val responseStub by lazy {
        AllUsersResponse(
            listOf(userStub1, userStub2).map {
                UserResponse.of(it)
            }
        )
    }

    @BeforeEach
    fun setUp() {
        queryAllUsersByUserRoleUseCase = QueryAllUsersByUserRoleUseCase(
            userService
        )
    }

    @Test
    fun `전체 학생 조회 성공`() {

        // given
        given(userService.queryAllUserByRoleContainingOrderByEmail(userRole))
            .willReturn(listOf(userStub1, userStub2))

        // when
        val response = queryAllUsersByUserRoleUseCase.execute(userRole)

        // then
        assertThat(response).isEqualTo(responseStub)
    }

}