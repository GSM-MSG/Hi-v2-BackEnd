package team.msg.hiv2.domain.user.presentation.data.response

import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import java.util.*

data class StudentResponse(
    val userId: UUID,
    val email: String,
    val name: String,
    val schoolNumber: String?,
    val profileImageUrl: String,
    val useStatus: UseStatus
) {
    companion object {
        fun of(user: User) = StudentResponse(
            userId = user.id,
            email = user.email,
            name = user.name,
            schoolNumber = user.schoolNumber,
            profileImageUrl = user.profileImageUrl,
            useStatus = user.useStatus
        )
    }
}