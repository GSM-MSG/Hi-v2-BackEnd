package team.msg.hiv2.domain.user.application.facade

import org.springframework.stereotype.Component
import team.msg.hiv2.domain.user.application.usecase.QueryAllUsersByUserRoleUseCase
import team.msg.hiv2.domain.user.application.usecase.QueryAllUsersUseCase
import team.msg.hiv2.domain.user.application.usecase.QueryMyRoleUseCase
import team.msg.hiv2.domain.user.application.usecase.QueryUserInfoUseCase
import team.msg.hiv2.domain.user.application.usecase.SearchStudentByNameKeywordUseCase
import team.msg.hiv2.domain.user.application.usecase.SearchUserByNameKeywordUseCase
import team.msg.hiv2.domain.user.application.usecase.UpdateUserRoleUseCase
import team.msg.hiv2.domain.user.application.usecase.UpdateUserUseStatusUseCase
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.presentation.data.request.SearchUserKeywordRequest
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserRoleWebRequest
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserUseStatusRequest
import team.msg.hiv2.domain.user.presentation.data.response.AllUsersResponse
import team.msg.hiv2.domain.user.presentation.data.response.StudentResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserInfoResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserRoleResponse
import java.util.*

@Component
class UserFacadeImpl(
    private val queryAllUsersUseCase: QueryAllUsersUseCase,
    private val queryAllUsersByUserRoleUseCase: QueryAllUsersByUserRoleUseCase,
    private val queryUserInfoUseCase: QueryUserInfoUseCase,
    private val queryMyRoleUseCase: QueryMyRoleUseCase,
    private val searchUserByNameKeywordUseCase: SearchUserByNameKeywordUseCase,
    private val updateUserUseStatusUseCase: UpdateUserUseStatusUseCase,
    private val updateUserRoleUseCase: UpdateUserRoleUseCase,
    private val searchStudentByNameKeywordUseCase: SearchStudentByNameKeywordUseCase
) : UserFacade {

    override fun queryAllUsers(): AllUsersResponse =
        queryAllUsersUseCase.execute()

    override fun queryAllUsersByUserRole(userRole: UserRole): AllUsersResponse =
        queryAllUsersByUserRoleUseCase.execute(userRole)

    override fun queryUserInfo(): UserInfoResponse =
        queryUserInfoUseCase.execute()

    override fun queryMyRole(): UserRoleResponse =
        queryMyRoleUseCase.execute()

    override fun searchUserByNameKeyword(request: SearchUserKeywordRequest): List<UserResponse> =
        searchUserByNameKeywordUseCase.execute(request)

    override fun updateUserUseStatus(userId: UUID, request: UpdateUserUseStatusRequest) =
        updateUserUseStatusUseCase.execute(userId, request)

    override fun updateUserRole(userId: UUID, request: UpdateUserRoleWebRequest) =
        updateUserRoleUseCase.execute(userId, request)

    override fun searchStudentByNameKeyword(request: SearchUserKeywordRequest): List<StudentResponse> =
        searchStudentByNameKeywordUseCase.execute(request)

}