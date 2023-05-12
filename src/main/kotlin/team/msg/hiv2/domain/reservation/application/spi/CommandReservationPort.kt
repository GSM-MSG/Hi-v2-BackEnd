package team.msg.hiv2.domain.reservation.application.spi

import team.msg.hiv2.domain.reservation.domain.Reservation
import java.util.UUID

interface CommandReservationPort {
    fun saveReservation(reservation: Reservation): UUID
}