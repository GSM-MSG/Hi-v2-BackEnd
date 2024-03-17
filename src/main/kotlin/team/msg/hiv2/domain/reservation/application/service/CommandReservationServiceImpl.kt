package team.msg.hiv2.domain.reservation.application.service

import team.msg.hiv2.domain.reservation.application.spi.CommandReservationPort
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class CommandReservationServiceImpl(
    private val commandReservationPort: CommandReservationPort
) : CommandReservationService {

    override fun save(reservation: Reservation): Reservation =
        commandReservationPort.save(reservation)

    override fun delete(reservation: Reservation) {
        commandReservationPort.delete(reservation)
    }

    override fun deleteAll() {
        commandReservationPort.deleteAllInBatch()
    }

    override fun deleteAllReservationInBatch(reservations: List<Reservation>) {
        commandReservationPort.deleteAllReservationInBatch(reservations)
    }

}