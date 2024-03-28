package team.msg.hiv2.domain.homebase.application.usecase

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
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

    private lateinit var reserveHomeBaseUseCase: ReserveHomeBaseUseCase

    private val floor = 3
    private val period = 10
    private val reason = "회의"
    private val homeBaseNumber = 1
    private val maxCapacity = 4

    private val homeBaseId = 1L
    private val userId1 = UUID.randomUUID()
    private val userId2 = UUID.randomUUID()

    private val homeBaseStub by lazy {
        HomeBase(
            id = homeBaseId,
            period = period,
            floor = floor,
            homeBaseNumber = homeBaseNumber,
            maxCapacity = maxCapacity
        )
    }

    private val reservationStub by lazy {
        Reservation(
            id = UUID.randomUUID(),
            homeBaseId = homeBaseStub.id,
            reason = reason,
            checkStatus = false,
            userIds = mutableListOf(userId1)
        )
    }

    private val userStub1 by lazy {
        User(
            id = userId1,
            email = "test1@email",
            name = "hope1",
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
            email = "test2@email",
            name = "hope2",
            grade = 2,
            classNum = 4,
            number = 7,
            profileImageUrl = "profileImageUrl2",
            role = UserRole.ROLE_STUDENT,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val requestStub by lazy {
        ReservationHomeBaseRequest(
            mutableListOf(userId2),
            reason
        )
    }

    @BeforeEach
    fun setUp(){
        reserveHomeBaseUseCase = ReserveHomeBaseUseCase(
            userService, reservationService, homeBaseService
        )
    }

    @Test
    fun `예약 성공`(){

        given(homeBaseService.queryHomeBaseByFloorAndPeriodAndHomeBaseNumber(floor, period, homeBaseNumber))
            .willReturn(homeBaseStub)

        given(reservationService.existsByHomeBaseId(homeBaseId))
            .willReturn(false)

        given(userService.queryAllUserById(requestStub.users))
            .willReturn(listOf(userStub2))

        given(homeBaseService.queryHomeBaseByPeriod(period))
            .willReturn(listOf(homeBaseStub))

        given(reservationService.queryAllReservationByHomeBaseIn(listOf(homeBaseStub)))
            .willReturn(listOf(reservationStub))

        given(reservationService.save(any()))
            .willReturn(reservationStub)

        assertDoesNotThrow {
            reserveHomeBaseUseCase.execute(floor, period, homeBaseNumber, requestStub)
        }
    }

}