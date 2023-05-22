package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class DelegateRepresentativeUseCase(
    private val userPort: UserPort
) {

    fun execute(reservationId: UUID, userId: UUID){
        
    }
}