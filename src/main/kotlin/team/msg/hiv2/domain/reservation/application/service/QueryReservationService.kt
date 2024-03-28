package team.msg.hiv2.domain.reservation.application.service

import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.reservation.domain.Reservation
import java.util.*

interface QueryReservationService {

    fun queryReservationById(id: UUID): Reservation
    fun queryReservationByHomeBase(homeBase: HomeBase): Reservation?
    fun queryAllReservationByHomeBaseIn(homeBases: List<HomeBase>): List<Reservation>
    fun queryAllReservation(): List<Reservation>
    fun countReservationByHomeBase(homeBase: HomeBase): Int
    fun existsByHomeBaseId(homeBase: HomeBase): Boolean
    fun queryAllReservationByUserIdInOrderByReservationId(userId: UUID): List<Reservation>
}