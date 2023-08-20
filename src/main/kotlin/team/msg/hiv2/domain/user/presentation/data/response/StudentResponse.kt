package team.msg.hiv2.domain.user.presentation.data.response

import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import java.util.UUID

data class StudentResponse(
    val userId: UUID,
    val name: String,
    val grade: Int,
    val classNum: Int,
    val number: Int,
    val profileImageUrl: String,
    val useStatus: UseStatus
) {
    companion object {
        fun of(user: User) = StudentResponse(
            userId = user.id,
            name = user.name,
            grade = user.grade!!,
            classNum = user.classNum!!,
            number = user.number!!,
            profileImageUrl = user.profileImageUrl,
            useStatus = user.useStatus
        )
    }
}