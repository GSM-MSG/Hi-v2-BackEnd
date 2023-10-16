package team.msg.hiv2.domain.user.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.hiv2.domain.user.application.facade.UserFacade
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.presentation.data.request.SearchUserKeywordRequest
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserRoleWebRequest
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserUseStatusRequest
import team.msg.hiv2.domain.user.presentation.data.response.AllUsersResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserInfoResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserRoleResponse
import team.msg.hiv2.domain.user.presentation.data.web.UpdateUserUseStatusWebRequest
import java.util.UUID
import javax.validation.Valid
import team.msg.hiv2.domain.user.presentation.data.response.StudentResponse

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val userFacade: UserFacade
) {

    @GetMapping("/search")
    fun searchUser(@RequestParam keyword: String): ResponseEntity<List<UserResponse>> =
        userFacade.searchUserByNameKeyword(
            SearchUserKeywordRequest(
                keyword = keyword
            )
        )
            .let { ResponseEntity.ok(it) }

    @GetMapping("/my-page")
    fun queryUserInfo(): ResponseEntity<UserInfoResponse> =
        userFacade.queryUserInfo()
            .let { ResponseEntity.ok(it) }

    @GetMapping
    fun queryAllUsersByUserRole(@RequestParam userRole: UserRole): ResponseEntity<AllUsersResponse> =
        userFacade.queryAllUsersByUserRole(userRole)
            .let { ResponseEntity.ok(it) }

    @GetMapping("/my-role")
    fun queryMyRole(): ResponseEntity<UserRoleResponse> =
        userFacade.queryMyRole()
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/{id}")
    fun updateUserUseStatus(@PathVariable id: UUID, @RequestBody @Valid request: UpdateUserUseStatusWebRequest): ResponseEntity<Void> =
        userFacade.updateUserUseStatus(id,
            UpdateUserUseStatusRequest(
                status = request.status
            )
        )
            .let { ResponseEntity.noContent().build() }

    @PatchMapping("/{id}/role")
    fun updateUserRole(@PathVariable id: UUID, @RequestBody @Valid request: UpdateUserRoleWebRequest): ResponseEntity<Void> =
        userFacade.updateUserRole(id,
            UpdateUserRoleWebRequest(
                role = request.role
            )
        )
            .let { ResponseEntity.noContent().build() }

    @GetMapping("/search-student")
    fun searchStudent(@RequestParam keyword: String): ResponseEntity<List<StudentResponse>> =
        userFacade.searchStudentByNameKeyword(
            SearchUserKeywordRequest(keyword)
        )
            .let { ResponseEntity.ok(it) }
}