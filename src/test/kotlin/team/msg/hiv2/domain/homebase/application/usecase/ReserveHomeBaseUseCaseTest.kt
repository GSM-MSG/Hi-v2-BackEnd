package team.msg.hiv2.domain.homebase.application.usecase

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
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
internal class ReserveHomeBaseUseCaseTest {

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var reservationService: ReservationService

    @Mock
    private lateinit var homeBaseService: HomeBaseService

    @Mock
    private lateinit var teamService: TeamService

    private lateinit  var reserveHomeBaseUseCase: ReserveHomeBaseUseCase

    private val floor = 3
    private val period = 10
    private val reason = "회의"
    private val reservationNumber = 1

    private val userId = UUID.randomUUID()
    private val userId2 = UUID.randomUUID()
    private val reservationCount = 3

    private val requestStub by lazy {
        ReservationHomeBaseRequest(
            mutableListOf(userId, userId2),
            reason,
            reservationNumber
        )
    }

    private val homeBaseStub by lazy {
        HomeBase(
            id = 1,
            period = period,
            floor = floor
        )
    }

    private val teamStub by lazy {
        Team(
            id = UUID.randomUUID(),
            userIds = mutableListOf(userId, userId2)
        )
    }

    private val reservationStub by lazy {
        Reservation(
            id = UUID.randomUUID(),
            homeBaseId = homeBaseStub.id,
            reason = reason,
            checkStatus = false,
            reservationNumber = reservationNumber,
            teamId = teamStub.id
        )
    }

    @BeforeEach
    fun setUp(){
        reserveHomeBaseUseCase = ReserveHomeBaseUseCase(
            userService,
            reservationService,
            homeBaseService,
            teamService
        )
    }

    @Test
    fun `예약 성공`(){
        val userStub = User(
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

        val userStub2 = User(
            id = userId2,
            email = "test2@email",
            name = "hope2",
            grade = 2,
            classNum = 4,
            number = 7,
            profileImageUrl = "profileImageUrl2",
            role = UserRole.ROLE_STUDENT,
            useStatus = UseStatus.AVAILABLE
        )

        given(homeBaseService.queryHomeBaseByFloorAndPeriod(floor, period)).willReturn(homeBaseStub)

        given(userService.queryAllUserById(requestStub.users)).willReturn(listOf(userStub, userStub2))

        given(reservationService.countReservationByHomeBase(homeBaseStub)).willReturn(reservationCount)

        given(teamService.save(any())).willReturn(teamStub)

        given(reservationService.save(any())).willReturn(reservationStub)

        assertDoesNotThrow {
            reserveHomeBaseUseCase.execute(floor, period, requestStub)
        }
    }

}