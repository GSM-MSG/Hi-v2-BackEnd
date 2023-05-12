package team.msg.hiv2.domain.homebase.application.usecase

import org.hibernate.annotations.Check
import team.msg.hiv2.domain.homebase.application.spi.QueryHomeBasePort
import team.msg.hiv2.domain.homebase.exception.HomeBaseNotFoundException
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import team.msg.hiv2.domain.reservation.application.spi.CommandReservationPort
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.*

@UseCase
class ReserveHomeBaseUseCase(
    private val userPort: UserPort,
    private val commandReservationPort: CommandReservationPort,
    private val queryHomeBasePort: QueryHomeBasePort
) {

    fun execute(floor: Int, period: Int, request: ReservationHomeBaseRequest){
        val currentUser = userPort.queryCurrentUser()
        val homeBase = queryHomeBasePort.queryHomeBaseByFloorAndPeriod(floor, period)
            ?: throw HomeBaseNotFoundException()

        val reservation = Reservation(
            id = UUID.randomUUID(),
            reason = request.reason,
            representativeId = currentUser.id,
            homeBaseId = homeBase.id,
            checkStatus = false
        )

        val users = userPort.queryAllUserById(request.users)
        val reservationId = commandReservationPort.saveReservation(reservation)
        userPort.saveAll(users.map { it.copy(reservationId = reservationId, useStatus = UseStatus.UNAVAILABLE) })
    }
}