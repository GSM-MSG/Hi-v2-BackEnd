package team.msg.hiv2.domain.reservation.application.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.given
import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.homebase.presentation.data.response.HomeBaseResponse
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationDetailResponse
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.team.domain.Team
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.HiTest
import java.util.*

@HiTest
class QueryReservationDetailUseCaseTest {

    @Mock
    private lateinit var reservationService: ReservationService

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var teamService: TeamService

    @Mock
    private lateinit var homeBaseService: HomeBaseService

    private lateinit var queryReservationDetailUseCase: QueryReservationDetailUseCase

    private val homeBaseId = 21L
    private val floor = 3
    private val period = 10
    private val homeBaseNumber = 1
    private val maxCapacity = 4

    private val userId1 = UUID.randomUUID()
    private val userId2 = UUID.randomUUID()

    private val teamId = UUID.randomUUID()

    private val reason = "회의"

    private val homeBaseStub by lazy {
        HomeBase(
            id = homeBaseId,
            floor = floor,
            period = period,
            homeBaseNumber = homeBaseNumber,
            maxCapacity = maxCapacity
        )
    }

    private val teamStub by lazy {
        Team(
            id = teamId,
            userIds = mutableListOf(userId1)
        )
    }

    private val reservationStub by lazy {
        Reservation(
            id = UUID.randomUUID(),
            reason = reason,
            homeBaseId = homeBaseStub.id,
            checkStatus = false,
            teamId = teamStub.id
        )
    }

    private val userStub1 by lazy {
        User(
            id = userId1,
            email = "test@email",
            name = "hope",
            grade = 2,
            classNum = 4,
            number = 6,
            profileImageUrl = "profileImageUrl",
            role = UserRole.ROLE_STUDENT,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val userStub2 by lazy {
        User(
            id = userId2,
            email = "test@email",
            name = "esperer",
            grade = 2,
            classNum = 4,
            number = 7,
            profileImageUrl = "profileImageUrl",
            role = UserRole.ROLE_STUDENT,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val userResponseStub1 by lazy {
        UserResponse.of(userStub1)

    }

    private val userResponseStub2 by lazy {
        UserResponse.of(userStub2)
    }

    private val homeBaseResponseStub by lazy {
        HomeBaseResponse.of(homeBaseStub)
    }

    private val responseStub by lazy {
        ReservationDetailResponse.of(reservationStub, listOf(userResponseStub1, userResponseStub2), homeBaseStub)
    }

    private val requestId = reservationStub.id

    @BeforeEach
    fun setUp() {
        queryReservationDetailUseCase =
            QueryReservationDetailUseCase(reservationService, userService, teamService, homeBaseService)
    }

    @Test
    fun `예약 테이블 조회 성공`(){

        // given
        given(reservationService.queryReservationById(requestId))
            .willReturn(reservationStub)

        given(homeBaseService.queryHomeBaseById(homeBaseId))
            .willReturn(homeBaseStub)

        given(teamService.queryTeamById(teamId))
            .willReturn(teamStub)

        given(userService.queryAllUserById(teamStub.userIds))
            .willReturn(listOf(userStub1, userStub2))

        // when
        val result = queryReservationDetailUseCase.execute(requestId)

        // then
        assertThat(result).isEqualTo(responseStub)
    }
}