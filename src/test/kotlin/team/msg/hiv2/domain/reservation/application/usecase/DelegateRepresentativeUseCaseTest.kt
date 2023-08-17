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
import java.util.UUID

@HiTest
class DelegateRepresentativeUseCaseTest {

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var reservationService: ReservationService

    private lateinit var delegateRepresentativeUseCase: DelegateRepresentativeUseCase

    private val reservationId = UUID.randomUUID()
    private val representativeId = UUID.randomUUID()
    private val userId = UUID.randomUUID()

    private val homeBaseStub by lazy {
        HomeBase(
            id = 1,
            floor = 3,
            period = 10
        )
    }

    private val reservationStub by lazy {
        Reservation(
            id =  reservationId,
            representativeId = representativeId,
            reason = "reason",
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

    @BeforeEach
    fun setUp() {
        delegateRepresentativeUseCase = DelegateRepresentativeUseCase(
            userService, reservationService
        )
    }

    @Test
    fun `대표자 위임 성공`() {
        // given
        given(userService.queryCurrentUser())
            .willReturn(userStub1)

        given(reservationService.queryReservationById(reservationId))
            .willReturn(reservationStub)

        given(userService.queryUserByIdAndReservation(userId, reservationStub))
            .willReturn(userStub2)

        // when & then

        assertDoesNotThrow {
            delegateRepresentativeUseCase.execute(reservationId, userId)
        }
    }

}