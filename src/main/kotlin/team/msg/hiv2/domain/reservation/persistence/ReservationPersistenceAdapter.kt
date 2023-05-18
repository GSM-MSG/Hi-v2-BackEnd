package team.msg.hiv2.domain.reservation.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.homebase.persistence.mapper.HomeBaseMapper
import team.msg.hiv2.domain.reservation.application.spi.ReservationPort
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.exception.ReservationNotFoundException
import team.msg.hiv2.domain.reservation.persistence.mapper.ReservationMapper
import team.msg.hiv2.domain.reservation.persistence.repository.ReservationRepository
import java.util.*

@Component
class ReservationPersistenceAdapter(
    private val reservationRepository: ReservationRepository,
    private val reservationMapper: ReservationMapper,
    private val homeBaseMapper: HomeBaseMapper
) : ReservationPort {

    override fun saveReservation(reservation: Reservation): Reservation =
        reservationMapper.toDomain(reservationRepository.save(reservationMapper.toEntity(reservation)))!!

    override fun deleteReservation(reservation: Reservation){
        reservationRepository.deleteById(reservation.id)
    }

    override fun queryReservationById(id: UUID) =
        reservationMapper.toDomain(reservationRepository.findByIdOrNull(id))

    override fun queryAllReservationByHomeBase(homeBase: HomeBase): List<Reservation> =
        reservationRepository.findAllByHomeBase(homeBaseMapper.toEntity(homeBase))
            .map { reservationMapper.toDomain(it)!! }

}