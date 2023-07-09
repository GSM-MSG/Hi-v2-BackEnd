package team.msg.hiv2.domain.reservation.application.facade

import org.springframework.stereotype.Component
import team.msg.hiv2.domain.reservation.application.usecase.*
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationCheckStatusRequest
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationDetailResponse
import java.util.*

@Component
class ReservationFacadeImpl(
    private val checkAndRestrictReservationUserUseCase: CheckAndRestrictReservationUserUseCase,
    private val delegateRepresentativeUseCase: DelegateRepresentativeUseCase,
    private val deleteAllReservationUseCase: DeleteAllReservationUseCase,
    private val deleteReservationUseCase: DeleteReservationUseCase,
    private val exitReservationUseCase: ExitReservationUseCase,
    private val queryReservationDetailUseCase: QueryReservationDetailUseCase,
    private val updateReservationCheckStatusUseCase: UpdateReservationCheckStatusUseCase,
    private val updateReservationUseCase: UpdateReservationUseCase
) : ReservationFacade {

    override fun checkAndRestrictReservation(id: UUID) =
        checkAndRestrictReservationUserUseCase.execute(id)

    override fun delegateRepresentative(reservationId: UUID, userId: UUID) =
        delegateRepresentativeUseCase.execute(reservationId, userId)

    override fun deleteAllReservation() =
        deleteAllReservationUseCase.execute()

    override fun deleteReservation(id: UUID) =
        deleteReservationUseCase.execute(id)

    override fun exitReservation(id: UUID) =
        exitReservationUseCase.execute(id)

    override fun queryReservationDetail(id: UUID): ReservationDetailResponse =
        queryReservationDetailUseCase.execute(id)

    override fun updateReservationCheckStatus(id: UUID, request: UpdateReservationCheckStatusRequest) =
        updateReservationCheckStatusUseCase.execute(id, request)

    override fun updateReservation(id: UUID, request: UpdateReservationRequest) =
        updateReservationUseCase.execute(id, request)

}