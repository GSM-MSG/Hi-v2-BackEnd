package team.msg.hiv2.domain.user.application.facade

import team.msg.hiv2.domain.user.presentation.data.request.SearchUserKeywordRequest
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserRoleWebRequest
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserUseStatusRequest
import team.msg.hiv2.domain.user.presentation.data.response.AllStudentsResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserInfoResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserRoleResponse
import java.util.*

interface UserFacade {
    fun queryAllStudents(): AllStudentsResponse
    fun queryUserInfo(): UserInfoResponse
    fun queryMyRole(): UserRoleResponse
    fun searchUserByNameKeyword(request: SearchUserKeywordRequest): List<UserResponse>
    fun updateUserUseStatus(userId: UUID, request: UpdateUserUseStatusRequest)
    fun updateUserRole(userId: UUID, request: UpdateUserRoleWebRequest)
}