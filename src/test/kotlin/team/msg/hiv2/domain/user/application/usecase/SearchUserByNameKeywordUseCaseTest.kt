package team.msg.hiv2.domain.user.application.usecase

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.given
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.presentation.data.request.SearchUserKeywordRequest
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.HiTest
import java.util.*

@HiTest
class SearchUserByNameKeywordUseCaseTest {

    @Mock
    private lateinit var userService: UserService

    private lateinit var searchUserByNameKeywordUseCase: SearchUserByNameKeywordUseCase

    private val userStub by lazy {
        User(
            id = UUID.randomUUID(),
            email = "test@email",
            name = "김희망",
            grade = 2,
            classNum = 4,
            number = 6,
            profileImageUrl = "profileImageUrl",
            roles = mutableListOf(UserRole.ROLE_STUDENT),
            reservationId = null,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val responseStub by lazy {
        UserResponse(
            userId = userStub.id,
            email = userStub.email,
            name = userStub.name,
            grade = userStub.grade,
            classNum = userStub.classNum,
            number = userStub.number,
            profileImageUrl = userStub.profileImageUrl
        )
    }

    private val requestStub by lazy {
        SearchUserKeywordRequest(
            keyword = "김"
        )
    }

    @BeforeEach
    fun setUp() {
        searchUserByNameKeywordUseCase =
            SearchUserByNameKeywordUseCase(userService)
    }

    @Test
    fun `유저 검색 성공`() {
        // given
        given(userService.queryUserByNameContainingOrderByEmail(requestStub.keyword))
            .willReturn(listOf(userStub))

        // when
        val result = searchUserByNameKeywordUseCase.execute(requestStub)

        // then
        assertThat(result).isEqualTo(listOf(responseStub))
    }
}