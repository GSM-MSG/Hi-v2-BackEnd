package team.msg.hiv2.domain.reservation.application.spi

import team.msg.hiv2.domain.reservation.domain.Reservation

interface CommandReservationPort {
    fun saveReservation(reservation: Reservation): Reservation
}