package team.msg.hiv2.domain.reservation.application.service

import team.msg.hiv2.domain.reservation.application.spi.CommandReservationPort
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class CommandReservationServiceImpl(
    private val commandReservationPort: CommandReservationPort
) : CommandReservationPort {

    override fun save(reservation: Reservation): Reservation =
        commandReservationPort.save(reservation)

    override fun delete(reservation: Reservation) =
        commandReservationPort.delete(reservation)

}