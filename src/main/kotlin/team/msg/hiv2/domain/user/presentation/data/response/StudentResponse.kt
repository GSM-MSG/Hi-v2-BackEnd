package team.msg.hiv2.domain.user.presentation.data.response

import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus

data class StudentResponse(
    val user: UserResponse,
    val useStatus: UseStatus
) {
    companion object {
        fun of(user: User) = StudentResponse(
            user = UserResponse(
                userId = user.id,
                name = user.name,
                grade = user.grade,
                classNum = user.classNum,
                number = user.number,
                profileImageUrl = user.profileImageUrl
            ),
            useStatus = user.useStatus
        )
    }
}