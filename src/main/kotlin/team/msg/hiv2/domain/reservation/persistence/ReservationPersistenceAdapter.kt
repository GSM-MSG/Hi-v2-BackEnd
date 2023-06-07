package team.msg.hiv2.domain.reservation.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.domain.HomeBase
import team.msg.hiv2.domain.homebase.persistence.mapper.HomeBaseMapper
import team.msg.hiv2.domain.reservation.application.spi.ReservationPort
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.persistence.mapper.ReservationMapper
import team.msg.hiv2.domain.reservation.persistence.repository.ReservationRepository
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.persistence.mapper.UserMapper
import java.util.*

@Component
class ReservationPersistenceAdapter(
    private val reservationRepository: ReservationRepository,
    private val reservationMapper: ReservationMapper,
    private val homeBaseMapper: HomeBaseMapper,
    private val userMapper: UserMapper
) : ReservationPort {

    override fun save(reservation: Reservation): Reservation =
        reservationMapper.toDomain(reservationRepository.save(reservationMapper.toEntity(reservation)))!!

    override fun deleteReservation(reservation: Reservation){
        reservationRepository.deleteById(reservation.id)
    }

    override fun queryReservationById(id: UUID) =
        reservationMapper.toDomain(reservationRepository.findByIdOrNull(id))

    override fun queryAllReservationByHomeBase(homeBase: HomeBase): List<Reservation> =
        reservationRepository.findAllByHomeBase(homeBaseMapper.toEntity(homeBase))
            .map { reservationMapper.toDomain(it)!! }

    override fun queryReservationByUser(user: User): Reservation? =
        reservationMapper.toDomain(reservationRepository.findByUser(userMapper.toEntity(user)))
}