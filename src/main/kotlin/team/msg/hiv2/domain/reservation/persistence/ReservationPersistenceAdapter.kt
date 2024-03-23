package team.msg.hiv2.domain.reservation.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.homebase.persistence.mapper.HomeBaseMapper
import team.msg.hiv2.domain.reservation.application.spi.ReservationPort
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.persistence.mapper.ReservationMapper
import team.msg.hiv2.domain.reservation.persistence.repository.ReservationRepository
import team.msg.hiv2.domain.team.domain.Team
import team.msg.hiv2.domain.team.persistence.mapper.TeamMapper
import java.util.*

@Component
class ReservationPersistenceAdapter(
    private val reservationRepository: ReservationRepository,
    private val reservationMapper: ReservationMapper,
    private val homeBaseMapper: HomeBaseMapper,
    private val teamMapper: TeamMapper
) : ReservationPort {

    override fun save(reservation: Reservation): Reservation =
        reservationMapper.toDomain(reservationRepository.save(reservationMapper.toEntity(reservation)))!!

    override fun delete(reservation: Reservation) {
        reservationRepository.deleteById(reservation.id)
    }

    override fun deleteAllInBatch() {
        reservationRepository.deleteAllInBatch()
    }

    override fun deleteAllReservationInBatch(reservations: List<Reservation>) {
        reservationRepository.deleteAllInBatch(reservations.map { reservationMapper.toEntity(it) })
    }

    override fun queryReservationById(id: UUID) =
        reservationMapper.toDomain(reservationRepository.findByIdOrNull(id))

    override fun queryReservationByHomeBase(homeBase: HomeBase): Reservation? =
        reservationMapper.toDomain(reservationRepository.findByHomeBase(homeBaseMapper.toEntity(homeBase)))

    override fun queryAllReservationByHomeBaseIn(homeBases: List<HomeBase>): List<Reservation> =
        reservationRepository.findAllByHomeBaseIn(homeBases.map { homeBaseMapper.toEntity(it) })
            .map { reservationMapper.toDomain(it)!! }

    override fun queryAllReservations(): List<Reservation> =
        reservationRepository.findAll().map { reservationMapper.toDomain(it)!! }

    override fun countReservationByHomeBase(homeBase: HomeBase): Int =
        reservationRepository.countByHomeBase(homeBaseMapper.toEntity(homeBase))

    override fun existsByHomeBase(homeBase: HomeBase): Boolean =
        reservationRepository.existsByHomeBase(homeBaseMapper.toEntity(homeBase))

    override fun queryAllReservationByTeamsOrderByReservationId(teams: List<Team>): List<Reservation> =
        reservationRepository.findAllByTeamInOrderByHomeBaseId(teams.map { teamMapper.toEntity(it) }).map { reservationMapper.toDomain(it)!! }

}