package team.msg.hiv2.domain.user.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.hiv2.domain.user.application.usecase.QueryAllStudentsUseCase
import team.msg.hiv2.domain.user.application.usecase.QueryUserInfoUseCase
import team.msg.hiv2.domain.user.application.usecase.SearchUserByNameKeywordUseCase
import team.msg.hiv2.domain.user.application.usecase.UpdateUserUseStatusUseCase
import team.msg.hiv2.domain.user.presentation.data.request.SearchUserKeywordRequest
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserUseStatusRequest
import team.msg.hiv2.domain.user.presentation.data.response.AllStudentsResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserInfoResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val searchUserByNameKeywordUseCase: SearchUserByNameKeywordUseCase,
    private val queryUserInfoUseCase: QueryUserInfoUseCase,
    private val queryAllStudentsUseCase: QueryAllStudentsUseCase,
    private val updateUserUseStatusUseCase: UpdateUserUseStatusUseCase
) {

    @GetMapping("/search")
    fun searchUser(@RequestBody @Valid request: SearchUserKeywordRequest): ResponseEntity<List<UserResponse>> =
        searchUserByNameKeywordUseCase.execute(request.keyword)
            .let { ResponseEntity.ok(it) }

    @GetMapping("/my-page")
    fun queryMyPage(): ResponseEntity<UserInfoResponse> =
        queryUserInfoUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/students")
    fun queryAllUser(): ResponseEntity<AllStudentsResponse> =
        queryAllStudentsUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/{id}")
    fun updateUserUseStatus(@PathVariable id: UUID, updateUserUseStatusRequest: UpdateUserUseStatusRequest): ResponseEntity<Void> =
        updateUserUseStatusUseCase.execute(id, updateUserUseStatusRequest.status)
            .let { ResponseEntity.noContent().build() }
}