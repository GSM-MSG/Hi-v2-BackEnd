package team.msg.hiv2.domain.user.application.validator

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.exception.ForbiddenReserveException
import team.msg.hiv2.domain.notice.exception.ForbiddenCommandNoticeException
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.reservation.exception.ForbiddenCommandReservationException
import team.msg.hiv2.domain.team.application.service.TeamService
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import java.util.*

@Component
class UserValidatorImpl(
    private val teamService: TeamService
) : UserValidator {

    private val log by lazy { LoggerFactory.getLogger(this.javaClass.simpleName) }

    override fun checkUserUseStatus(user: User) {
        if(user.useStatus == UseStatus.UNAVAILABLE) {
            log.warn("User {} is not available reservation status", user.id)
            throw ForbiddenReserveException()
        }
    }

    override fun checkUsersUseStatus(users: List<User>) {
        users.forEach { checkUserUseStatus(it) }
    }

    override fun checkUserAndReservation(user: User, reservation: Reservation) {
        val teams = teamService.queryAllTeamByUserId(reservation.teamId)
        val isContain = teams.any { it.userIds.contains(user.id) && it.id == reservation.teamId }

        if(!isContain) {
            log.warn("User {} is not contained in a reservation {}", user.id, reservation.id)
            throw ForbiddenCommandReservationException()
        }
    }

    override fun checkUserAndWriter(userId: UUID, noticeWriterId: UUID) {
        if(userId != noticeWriterId) {
            log.warn("User {} is not notice writer = {}", userId, noticeWriterId)
            throw ForbiddenCommandNoticeException()
        }
    }
}