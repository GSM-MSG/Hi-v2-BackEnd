package team.msg.hiv2.domain.reservation.application.spi

import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.reservation.domain.Reservation
import java.util.UUID

interface QueryReservationPort {
    fun queryReservationById(id: UUID): Reservation?
    fun queryAllReservationByHomeBase(homeBase: HomeBase): List<Reservation>
    fun queryAllReservationByHomeBaseIn(homeBases: List<HomeBase>): List<Reservation>
    fun countReservationByHomeBase(homeBase: HomeBase): Int
}