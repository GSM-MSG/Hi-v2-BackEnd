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
import team.msg.hiv2.domain.user.presentation.data.response.AllStudentsResponse
import team.msg.hiv2.domain.user.presentation.data.response.StudentResponse
import team.msg.hiv2.global.annotation.HiTest
import java.util.UUID

@HiTest
internal class QueryAllStudentsUseCaseTest {

    @Mock
    private lateinit var userService: UserService

    private lateinit var queryAllStudentsUseCase: QueryAllStudentsUseCase

    private val userId1 = UUID.randomUUID()
    private val userId2 = UUID.randomUUID()
    private val studentRole = UserRole.ROLE_STUDENT

    private val userStub1: User by lazy {
        User(
            id = userId1,
            email = "test1@email",
            name = "test1",
            grade = 2,
            classNum = 3,
            number = 6,
            profileImageUrl = "profileImageUrl",
            roles = mutableListOf(studentRole),
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
            roles = mutableListOf(studentRole),
            reservationId = null,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val responseStub by lazy {
        AllStudentsResponse(
            student = listOf(
                StudentResponse(
                    userId = userId1,
                    name = userStub1.name,
                    grade = userStub1.grade!!,
                    classNum = userStub1.classNum!!,
                    number = userStub1.number!!,
                    profileImageUrl = userStub1.profileImageUrl,
                    useStatus = userStub1.useStatus
                ),
                StudentResponse(
                    userId = userId2,
                    name = userStub2.name,
                    grade = userStub2.grade!!,
                    classNum = userStub2.classNum!!,
                    number = userStub2.number!!,
                    profileImageUrl = userStub2.profileImageUrl,
                    useStatus = userStub2.useStatus
                )
            )
        )
    }

    @BeforeEach
    fun setUp() {
        queryAllStudentsUseCase = QueryAllStudentsUseCase(
            userService
        )
    }

    @Test
    fun `전체 학생 조회 성공`() {

        // given
        given(userService.queryAllUserByRolesContaining(studentRole))
            .willReturn(listOf(userStub1, userStub2))

        // when
        val response = queryAllStudentsUseCase.execute()

        // then
        assertThat(response).isEqualTo(responseStub)
    }

}