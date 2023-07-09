package team.msg.hiv2.domain.user.application.facade

import org.springframework.stereotype.Component
import team.msg.hiv2.domain.user.application.usecase.QueryAllStudentsUseCase
import team.msg.hiv2.domain.user.application.usecase.QueryUserInfoUseCase
import team.msg.hiv2.domain.user.application.usecase.SearchUserByNameKeywordUseCase
import team.msg.hiv2.domain.user.application.usecase.UpdateUserUseStatusUseCase
import team.msg.hiv2.domain.user.presentation.data.request.SearchUserKeywordRequest
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserUseStatusRequest
import team.msg.hiv2.domain.user.presentation.data.response.AllStudentsResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserInfoResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import java.util.*

@Component
class UserFacadeImpl(
    private val queryAllStudentsUseCase: QueryAllStudentsUseCase,
    private val queryUserInfoUseCase: QueryUserInfoUseCase,
    private val searchUserByNameKeywordUseCase: SearchUserByNameKeywordUseCase,
    private val updateUserUseStatusUseCase: UpdateUserUseStatusUseCase
) : UserFacade {

    override fun queryAllStudents(): AllStudentsResponse =
        queryAllStudentsUseCase.execute()

    override fun queryUserInfo(): UserInfoResponse =
        queryUserInfoUseCase.execute()

    override fun searchUserByNameKeyword(request: SearchUserKeywordRequest): List<UserResponse> =
        searchUserByNameKeywordUseCase.execute(request)

    override fun updateUserUseStatus(userId: UUID, request: UpdateUserUseStatusRequest) =
        updateUserUseStatusUseCase.execute(userId, request)

}