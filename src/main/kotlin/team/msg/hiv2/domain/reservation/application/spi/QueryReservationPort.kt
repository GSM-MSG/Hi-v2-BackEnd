package team.msg.hiv2.domain.reservation.application.spi

import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.reservation.domain.Reservation
import java.util.UUID

interface QueryReservationPort {

    fun queryReservationById(id: UUID): Reservation?
    fun queryReservationByHomeBase(homeBase: HomeBase): Reservation?
    fun queryAllReservationByHomeBaseIn(homeBases: List<HomeBase>): List<Reservation>
    fun queryAllReservations(): List<Reservation>
    fun countReservationByHomeBase(homeBase: HomeBase): Int
    fun existsByHomeBaseId(homeBaseId: Long): Boolean
    fun queryAllReservationByUserIdInOrderByHomeBaseId(userId: List<UUID>): List<Reservation>
}