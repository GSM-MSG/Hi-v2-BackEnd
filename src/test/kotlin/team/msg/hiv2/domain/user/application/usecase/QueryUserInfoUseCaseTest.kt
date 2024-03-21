package team.msg.hiv2.domain.user.application.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mock
import org.mockito.kotlin.given
import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.team.domain.Team
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.HiTest
import java.util.*

@HiTest
internal class QueryUserInfoUseCaseTest {

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var reservationService: ReservationService

    @Mock
    private lateinit var homeBaseService: HomeBaseService

    @Mock
    private lateinit var teamService: TeamService

    private lateinit var queryUserInfoUseCase: QueryUserInfoUseCase

    private val userId = UUID.randomUUID()
    private val reservationId = UUID.randomUUID()


    private val userStub: User by lazy {
        User(
            id = userId,
            email = "test@email",
            name = "test",
            grade = 2,
            classNum = 3,
            number = 6,
            profileImageUrl = "profileImageUrl",
            role = UserRole.ROLE_ADMIN,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val teamStub by lazy {
        Team(
            id = UUID.randomUUID(),
            userIds = mutableListOf(userId)
        )
    }

    private val reservationStub: Reservation by lazy {
        Reservation(
            id = reservationId,
            homeBaseId = 1,
            reason = "test",
            checkStatus = false,
            teamId = teamStub.id
        )
    }

    private val homeBaseStub: HomeBase by lazy {
        HomeBase(
            id = 1,
            floor = 3,
            period = 8,
            homeBaseNumber = 1,
            maxCapacity = 4
        )
    }

    @BeforeEach
    fun setUp() {
        queryUserInfoUseCase = QueryUserInfoUseCase(
            userService, reservationService, homeBaseService, teamService
        )
    }

    @Test
    fun `마이 페이지 조회 성공`() {

        // given
        given(userService.queryCurrentUser())
            .willReturn(userStub)

        given(teamService.queryAllTeamByUserIdsIn(listOf(userId)))
            .willReturn(listOf(teamStub))

        given(reservationService.queryAllReservationByTeamsOrderByTeam(listOf(teamStub)))
            .willReturn(listOf(reservationStub))

        given(homeBaseService.queryHomeBaseById(reservationStub.homeBaseId))
            .willReturn(homeBaseStub)

        // when & then
        assertDoesNotThrow {
            queryUserInfoUseCase.execute()
        }
    }

}