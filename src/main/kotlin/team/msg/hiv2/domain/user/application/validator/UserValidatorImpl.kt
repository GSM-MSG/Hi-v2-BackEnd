package team.msg.hiv2.domain.user.application.validator

import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.exception.ForbiddenReserveException
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus

@Component
class UserValidatorImpl : UserValidator {

    override fun checkUserUseStatus(user: User){
        if(user.useStatus == UseStatus.UNAVAILABLE)
            throw ForbiddenReserveException()
    }

    override fun checkUsersUseStatus(users: List<User>){
        users.forEach { checkUserUseStatus(it) }
    }
}