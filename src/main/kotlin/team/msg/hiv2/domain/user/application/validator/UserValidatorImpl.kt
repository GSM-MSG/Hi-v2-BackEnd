package team.msg.hiv2.domain.user.application.validator

import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.exception.ForbiddenReserveException
import team.msg.hiv2.domain.notice.exception.ForbiddenCommandNotice
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.exception.ForbiddenCommandReservationException
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import java.util.*

@Component
class UserValidatorImpl : UserValidator {

    override fun checkUserUseStatus(user: User) {
        if (user.useStatus == UseStatus.UNAVAILABLE)
            throw ForbiddenReserveException()
    }

    override fun checkUsersUseStatus(users: List<User>) {
        users.forEach { checkUserUseStatus(it) }
    }

    override fun checkRepresentative(user: User, reservation: Reservation) {
        if(user.id != reservation.representativeId)
            throw ForbiddenReserveException()
    }

    override fun checkUserAndReservation(user: User, reservation: Reservation) {
        if(user.reservationId != reservation.id)
            throw ForbiddenCommandReservationException()
    }

    override fun checkUserAndWriter(userId: UUID, noticeWriterId: UUID) {
        if(userId != noticeWriterId)
            throw ForbiddenCommandNotice()
    }
}