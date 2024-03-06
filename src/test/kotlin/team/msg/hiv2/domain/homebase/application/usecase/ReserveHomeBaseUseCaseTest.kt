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

    private lateinit  var reserveHomeBaseUseCase: ReserveHomeBaseUseCase

    private val floor = 3
    private val period = 10
    private val reason = "회의"
    private val reservationNumber = 1

    private val userId = UUID.randomUUID()
    private val userId2 = UUID.randomUUID()

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

    private val reservationStub by lazy {
        Reservation(
            id = UUID.randomUUID(),
            homeBaseId = homeBaseStub.id,
            representativeId = userId,
            reason = reason,
            checkStatus = false,
            reservationNumber = reservationNumber
        )
    }

    @BeforeEach
    fun setUp(){
        reserveHomeBaseUseCase = ReserveHomeBaseUseCase(
            userService,
            reservationService,
            homeBaseService
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
            reservationId = null,
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
            reservationId = null,
            useStatus = UseStatus.AVAILABLE
        )

        given(userService.queryCurrentUser()).willReturn(userStub)

        given(homeBaseService.queryHomeBaseByFloorAndPeriod(floor, period)).willReturn(homeBaseStub)

        given(userService.queryAllUserById(requestStub.users)).willReturn(listOf(userStub, userStub2))

        given(reservationService.save(any())).willReturn(reservationStub)

        assertDoesNotThrow {
            reserveHomeBaseUseCase.execute(floor, period, requestStub)
        }

    }

}