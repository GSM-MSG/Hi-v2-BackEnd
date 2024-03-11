package team.msg.hiv2.domain.reservation.application.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.given
import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationDetailResponse
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

    private lateinit var queryReservationDetailUseCase: QueryReservationDetailUseCase

    private val floor = 3
    private val period = 10
    private val reservationNumber = 1

    private val representativeId = UUID.randomUUID()
    private val userId = UUID.randomUUID()

    private val reason = "회의"
    private val homeBaseStub by lazy {
        HomeBase(
            id = 1,
            floor = floor,
            period = period
        )
    }

    private val reservationStub by lazy {
        Reservation(
            id = UUID.randomUUID(),
            representativeId = representativeId,
            reason = reason,
            homeBaseId = homeBaseStub.id,
            checkStatus = false,
            reservationNumber = reservationNumber
        )
    }

    private val userStub1 by lazy {
        User(
            id = representativeId,
            email = "test@email",
            name = "hope",
            grade = 2,
            classNum = 4,
            number = 6,
            profileImageUrl = "profileImageUrl",
            role = UserRole.ROLE_STUDENT,
            reservationId = reservationStub.id,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val userStub2 by lazy {
        User(
            id = userId,
            email = "test@email",
            name = "esperer",
            grade = 2,
            classNum = 4,
            number = 7,
            profileImageUrl = "profileImageUrl",
            role = UserRole.ROLE_STUDENT,
            reservationId = reservationStub.id,
            useStatus = UseStatus.AVAILABLE
        )
    }

    private val userResponseStub1 by lazy {
        UserResponse.of(userStub1)

    }

    private val userResponseStub2 by lazy {
        UserResponse.of(userStub2)
    }

    private val responseStub by lazy {
        ReservationDetailResponse.of(reservationStub, listOf(userResponseStub1, userResponseStub2))
    }

    private val requestId = reservationStub.id

    @BeforeEach
    fun setUp() {
        queryReservationDetailUseCase =
            QueryReservationDetailUseCase(reservationService, userService)
    }

    @Test
    fun `예약 테이블 조회 성공`(){

        // given
        given(reservationService.queryReservationById(requestId))
            .willReturn(reservationStub)

        given(userService.queryAllUserByReservation(reservationStub))
            .willReturn(listOf(userStub1, userStub2))

        // when
        val result = queryReservationDetailUseCase.execute(requestId)

        // then
        assertThat(result).isEqualTo(responseStub)
    }
}