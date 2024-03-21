package team.msg.hiv2.domain.reservation.application.service

import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.team.domain.Team
import java.util.*

interface QueryReservationService {

    fun queryReservationById(id: UUID): Reservation
    fun queryReservationByHomeBase(homeBase: HomeBase): Reservation?
    fun queryAllReservationByHomeBaseIn(homeBases: List<HomeBase>): List<Reservation>
    fun queryAllReservation(): List<Reservation>
    fun countReservationByHomeBase(homeBase: HomeBase): Int
    fun existsByHomeBase(homeBase: HomeBase): Boolean
    fun queryAllReservationByTeamsOrderByTeam(teams: List<Team>): List<Reservation>
}