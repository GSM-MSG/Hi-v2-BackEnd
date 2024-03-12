package team.msg.hiv2.domain.reservation.application.spi

import team.msg.hiv2.domain.reservation.domain.Reservation

interface CommandReservationPort {
    fun save(reservation: Reservation): Reservation
    fun delete(reservation: Reservation)
    fun deleteAll()
    fun deleteAllReservationInBatch(reservations: List<Reservation>)
}