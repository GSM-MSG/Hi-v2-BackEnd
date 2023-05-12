package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.spi.QueryHomeBasePort
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import team.msg.hiv2.domain.reservation.application.spi.CommandReservationPort
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class ReserveHomeBaseUseCase(
    private val userPort: UserPort,
    private val commandReservationPort: CommandReservationPort,
    private val queryHomeBasePort: QueryHomeBasePort
) {

    fun execute(homeBaseId: Long, request: ReservationHomeBaseRequest){
        val currentUser = userPort.queryCurrentUser()

    }
}