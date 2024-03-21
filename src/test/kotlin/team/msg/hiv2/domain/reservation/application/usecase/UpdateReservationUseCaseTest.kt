package team.msg.hiv2.domain.reservation.application.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.team.domain.Team
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.annotation.HiTest
import java.util.*

@HiTest
class UpdateReservationUseCaseTest {

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var reservationService: ReservationService

    @Mock
    private lateinit var teamService: TeamService

    @Mock
    private lateinit var homeBaseService: HomeBaseService

    private lateinit var updateReservationUseCase: UpdateReservationUseCase

    private val floor = 3
    private val period = 10
    private val homeBaseNumber = 1
    private val maxCapacity = 4

    private val reservationId = UUID.randomUUID()
    private val homeBaseId = 1L
    private val userId = UUID.randomUUID()
    private val teamId1 = UUID.randomUUID()

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

    private val teamStub1 by lazy {
        Team(
            id = teamId1,
            userIds = mutableListOf(userId)
        )
    }

    private val reservationStub by lazy {
        Reservation(
            id = UUID.randomUUID(),
            reason = reason,
            homeBaseId = homeBaseStub.id,
            checkStatus = false,
            teamId = teamStub1.id
        )
    }

    private val userStub by lazy {
        User(
            id = userId,
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

    private val requestStub by lazy {
        UpdateReservationRequest(
            users = listOf(userId),
            reason = "수정됨"
        )
    }

    @BeforeEach
    fun setUp(){
        updateReservationUseCase = UpdateReservationUseCase(
            reservationService, userService, teamService, homeBaseService
        )
    }

    @Test
    fun `수정 성공`() {

        // given
        given(reservationService.queryReservationById(reservationId))
            .willReturn(reservationStub)

        given(homeBaseService.queryHomeBaseById(homeBaseId))
            .willReturn(homeBaseStub)

        given(homeBaseService.queryHomeBaseByPeriod(period))
            .willReturn(listOf(homeBaseStub))

        given(reservationService.queryAllReservationByHomeBaseIn(listOf(homeBaseStub)))
            .willReturn(listOf(reservationStub))

        given(teamService.queryAllTeamByIdIn(listOf(teamId1)))
            .willReturn(listOf(teamStub1))

        given(teamService.queryTeamById(teamId1))
            .willReturn(teamStub1)

        given(userService.queryAllUserById(listOf(userId)))
            .willReturn(listOf(userStub))

        given(teamService.save(any()))
            .willReturn(teamStub1)

        given(reservationService.save(any()))
            .willReturn(reservationStub)

        // when & then
        assertDoesNotThrow {
            updateReservationUseCase.execute(reservationId, requestStub)
        }
    }

}