package team.msg.hiv2.domain.reservation.application.spi

import team.msg.hiv2.domain.reservation.domain.Reservation
import java.util.UUID

interface QueryReservationPort {
    fun queryReservationById(id: UUID): Reservation?
}