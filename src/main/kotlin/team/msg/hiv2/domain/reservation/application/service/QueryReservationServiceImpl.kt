package team.msg.hiv2.domain.reservation.application.service

import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.reservation.application.spi.QueryReservationPort
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.exception.ReservationNotFoundException
import team.msg.hiv2.domain.team.domain.Team
import team.msg.hiv2.global.annotation.service.DomainService
import java.util.*

@DomainService
class QueryReservationServiceImpl(
    private val queryReservationPort: QueryReservationPort
) : QueryReservationService {

    override fun queryReservationById(id: UUID): Reservation =
        queryReservationPort.queryReservationById(id) ?: throw ReservationNotFoundException()

    override fun queryAllReservationByHomeBase(homeBase: HomeBase): List<Reservation> =
        queryReservationPort.queryAllReservationByHomeBase(homeBase)

    override fun queryAllReservationByHomeBaseIn(homeBases: List<HomeBase>): List<Reservation> =
        queryReservationPort.queryAllReservationByHomeBaseIn(homeBases)

    override fun queryAllReservation(): List<Reservation> =
        queryReservationPort.queryAllReservations()

    override fun countReservationByHomeBase(homeBase: HomeBase): Int =
        queryReservationPort.countReservationByHomeBase(homeBase)

    override fun existsByHomeBaseAndReservationNumber(homeBase: HomeBase,reservationNumber: Int): Boolean =
        queryReservationPort.existsByHomeBaseAndReservationNumber(homeBase, reservationNumber)

    override fun queryAllReservationByTeams(teams: List<Team>): List<Reservation> =
        queryReservationPort.queryAllReservationByTeams(teams)

}