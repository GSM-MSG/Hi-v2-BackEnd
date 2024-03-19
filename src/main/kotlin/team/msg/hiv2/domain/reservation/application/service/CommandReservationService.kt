package team.msg.hiv2.domain.reservation.application.service

import team.msg.hiv2.domain.reservation.domain.Reservation

interface CommandReservationService{

    fun save(reservation: Reservation): Reservation
    fun delete(reservation: Reservation)
    fun deleteAllInBatch()
    fun deleteAllReservationInBatch(reservations: List<Reservation>)
}