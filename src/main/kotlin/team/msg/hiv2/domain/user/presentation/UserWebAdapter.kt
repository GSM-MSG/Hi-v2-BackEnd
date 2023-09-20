package team.msg.hiv2.domain.user.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.hiv2.domain.user.application.facade.UserFacade
import team.msg.hiv2.domain.user.presentation.data.request.SearchUserKeywordRequest
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserRoleWebRequest
import team.msg.hiv2.domain.user.presentation.data.request.UpdateUserUseStatusRequest
import team.msg.hiv2.domain.user.presentation.data.response.AllStudentsResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserInfoResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.domain.user.presentation.data.response.UserRoleResponse
import team.msg.hiv2.domain.user.presentation.data.web.UpdateUserUseStatusWebRequest
import java.util.UUID

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

    @GetMapping("/students")
    fun queryAllStudents(): ResponseEntity<AllStudentsResponse> =
        userFacade.queryAllStudents()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/my-role")
    fun queryMyRole(): ResponseEntity<UserRoleResponse> =
        userFacade.queryMyRole()
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/{id}")
    fun updateUserUseStatus(@PathVariable id: UUID, request: UpdateUserUseStatusWebRequest): ResponseEntity<Void> =
        userFacade.updateUserUseStatus(id,
            UpdateUserUseStatusRequest(
                status = request.status
            )
        )
            .let { ResponseEntity.noContent().build() }

    @PatchMapping("/{id}/role")
    fun updateUserRole(@PathVariable id: UUID, request: UpdateUserRoleWebRequest): ResponseEntity<Void> =
        userFacade.updateUserRole(id,
            UpdateUserRoleWebRequest(
                role = request.role
            )
        )
            .let { ResponseEntity.noContent().build() }
}