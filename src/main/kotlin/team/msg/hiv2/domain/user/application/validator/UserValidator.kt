package team.msg.hiv2.domain.user.application.validator

import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.domain.User

interface UserValidator {
    fun checkUserUseStatus(user: User)
    fun checkUsersUseStatus(users: List<User>)
    fun checkRepresentative(user: User, reservation: Reservation)
    fun checkUserAndReservation(user: User, reservation: Reservation)

}