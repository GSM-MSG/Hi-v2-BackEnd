package team.msg.hiv2.domain.reservation.application.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mock
import org.mockito.kotlin.given
import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.global.annotation.HiTest
import java.util.*

@HiTest
class DeleteReservationUseCaseTest {

    @Mock
    private lateinit var reservationService: ReservationService

    @Mock
    private lateinit var userService: UserService

    private lateinit var deleteReservationUseCase: DeleteReservationUseCase

    private val floor = 3
    private val period = 10
    private val homeBaseNumber = 1
    private val maxCapacity = 4

    private val userId1 = UUID.randomUUID()
    private val userId2 = UUID.randomUUID()

    private val reason = "회의"

    private val homeBaseStub by lazy {
        HomeBase(
            id = 1,
            floor = floor,
            period = period,
            homeBaseNumber =  homeBaseNumber,
            maxCapacity = maxCapacity
        )
    }

    private val reservationStub by lazy {
        Reservation(
            id = UUID.randomUUID(),
            reason = reason,
            homeBaseId = homeBaseStub.id,
            checkStatus = false,
            userIds = listOf(userId1, userId2).toMutableList()
        )
    }

    private val requestReservationId = reservationStub.id

    @BeforeEach
    fun setUp() {
        deleteReservationUseCase = DeleteReservationUseCase(
            reservationService, userService
        )
    }

    @Test
    fun `삭제 성공`() {

        // given
        given(reservationService.queryReservationById(requestReservationId))
            .willReturn(reservationStub)

        // when & then
        assertDoesNotThrow {
            deleteReservationUseCase.execute(requestReservationId)
        }
    }
}