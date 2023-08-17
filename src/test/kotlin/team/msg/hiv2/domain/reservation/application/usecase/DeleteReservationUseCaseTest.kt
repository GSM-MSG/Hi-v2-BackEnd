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
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
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
            checkStatus = false
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
            roles = mutableListOf(UserRole.ROLE_STUDENT),
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
            roles = mutableListOf(UserRole.ROLE_STUDENT),
            reservationId = reservationStub.id,
            useStatus = UseStatus.AVAILABLE
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

        given(userService.queryAllUserByReservation(reservationStub))
            .willReturn(listOf(userStub1, userStub2))

        // when & then
        assertDoesNotThrow {
            deleteReservationUseCase.execute(requestReservationId)
        }
    }
}