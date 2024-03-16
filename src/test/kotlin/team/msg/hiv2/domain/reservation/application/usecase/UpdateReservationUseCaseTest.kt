package team.msg.hiv2.domain.reservation.application.usecase

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.given
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

    private lateinit var updateReservationUseCase: UpdateReservationUseCase

    private val floor = 3
    private val period = 10
    private val reservationNumber = 1

    private val userId1 = UUID.randomUUID()
    private val teamId = UUID.randomUUID()

    private val reason = "회의"
    private val homeBaseStub by lazy {
        HomeBase(
            id = 1,
            floor = floor,
            period = period
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
            reservationNumber = reservationNumber,
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

    private val requestReservationId = reservationStub.id

    private val requestStub by lazy{
        UpdateReservationRequest(
            users = listOf(userId1),
            reason = "수정됨"
        )
    }

    @BeforeEach
    fun setUp(){
        updateReservationUseCase = UpdateReservationUseCase(
            reservationService, userService, teamService
        )
    }

    @Test
    fun `수정 성공`() {

        // given
        given(reservationService.queryReservationById(userId1))
            .willReturn(reservationStub)

        given(teamService.queryTeamById(teamId))
            .willReturn(teamStub)

        given(userService.queryUserById(userId1))
            .willReturn(userStub1)

        given(teamService.save(any()))
            .willReturn(teamStub)

        given(reservationService.save(any()))
            .willReturn(reservationStub)

        // when & then
        assertDoesNotThrow {
            updateReservationUseCase.execute(userId1, requestStub)
        }
    }

}