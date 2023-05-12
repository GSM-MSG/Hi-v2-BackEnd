package team.msg.hiv2.domain.reservation.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.reservation.application.spi.ReservationPort
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.persistence.mapper.ReservationMapper
import team.msg.hiv2.domain.reservation.persistence.repository.ReservationRepository
import java.util.*

@Component
class ReservationPersistenceAdapter(
    private val reservationRepository: ReservationRepository,
    private val reservationMapper: ReservationMapper
) : ReservationPort {

    override fun saveReservation(reservation: Reservation): UUID =
        reservationMapper.toDomain(reservationRepository.save(reservationMapper.toEntity(reservation)))!!.id


    override fun queryReservationById(id: UUID): Reservation? =
        reservationMapper.toDomain(reservationRepository.findByIdOrNull(id))

}