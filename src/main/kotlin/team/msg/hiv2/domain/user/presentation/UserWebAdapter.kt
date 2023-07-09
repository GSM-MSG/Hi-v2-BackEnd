package team.msg.hiv2.domain.user.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.hiv2.domain.user.application.facade.UserFacade
import team.msg.hiv2.domain.user.application.usecase.QueryAllStudentsUseCase
import team.msg.hiv2.domain.user.application.usecase.QueryUserInfoUseCase
import team.msg.hiv2.domain.user.application.usecase.SearchUserByNameKeywordUseCase
import team.msg.hiv2.domain.user.application.usecase.UpdateUserUseStatusUseCase
import team.msg.hiv2.domain.user.presentation.data.request.SearchUserKeywordRequest
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserUseStatusRequest
import team.msg.hiv2.domain.user.presentation.data.response.AllStudentsResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserInfoResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.domain.user.presentation.data.web.SearchUserKeywordWebRequest
import team.msg.hiv2.domain.user.presentation.data.web.UpdateUserUseStatusWebRequest
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val userFacade: UserFacade
) {

    @GetMapping("/search")
    fun searchUser(@RequestBody @Valid request: SearchUserKeywordWebRequest): ResponseEntity<List<UserResponse>> =
        userFacade.searchUserByNameKeyword(
            SearchUserKeywordRequest(
                keyword = request.keyword
            )
        )
            .let { ResponseEntity.ok(it) }

    @GetMapping("/my-page")
    fun queryUserInfo(): ResponseEntity<UserInfoResponse> =
        userFacade.queryUserInfo()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/students")
    fun queryAllStudents(): ResponseEntity<AllStudentsResponse> =
        userFacade.queryAllStudents()
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/{id}")
    fun updateUserUseStatus(@PathVariable id: UUID, request: UpdateUserUseStatusWebRequest): ResponseEntity<Void> =
        userFacade.updateUserUseStatus(id,
            UpdateUserUseStatusRequest(
                status = request.status
            )
        )
            .let { ResponseEntity.noContent().build() }
}