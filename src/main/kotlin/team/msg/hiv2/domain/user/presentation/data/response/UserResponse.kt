package team.msg.hiv2.domain.user.presentation.data.response

import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.domain.user.domain.constant.UserRole
import java.util.UUID

data class UserResponse(
    val userId: UUID,
    val email: String,
    val name: String,
    val grade: Int?,
    val classNum: Int?,
    val number: Int?,
    val profileImageUrl: String,
    val roles: MutableList<UserRole>,
    val useStatus: UseStatus
) {
    companion object {
        fun of(user: User) = UserResponse(
            userId = user.id,
            email = user.email,
            name = user.name,
            grade = user.grade,
            classNum = user.classNum,
            number = user.number,
            profileImageUrl = user.profileImageUrl,
            roles = user.roles,
            useStatus = user.useStatus
        )
    }
}
