package team.msg.hiv2.domain.user.presentation.data.response

import team.msg.hiv2.domain.user.domain.User
import java.util.UUID

data class UserResponse(
    val userId: UUID,
    val name: String,
    val grade: Int?,
    val classNum: Int?,
    val number: Int?
) {
    companion object {
        fun of(user: User): UserResponse = UserResponse(
            userId = user.id,
            name = user.name,
            grade = user.grade,
            classNum = user.classNum,
            number = user.number
        )
    }
}
