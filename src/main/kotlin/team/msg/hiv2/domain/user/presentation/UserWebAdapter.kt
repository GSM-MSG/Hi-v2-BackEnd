package team.msg.hiv2.domain.user.presentation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.hiv2.domain.user.application.usecase.SearchUserByNameKeywordUseCase
import team.msg.hiv2.domain.user.presentation.data.request.SearchUserKeywordRequest
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val searchUserByNameKeywordUseCase: SearchUserByNameKeywordUseCase
) {

    @GetMapping("path")
    fun execute(@RequestBody @Valid request: SearchUserKeywordRequest): ResponseEntity<List<UserResponse>> =
        searchUserByNameKeywordUseCase.execute(request.keyword)
            .let { ResponseEntity.ok(it) }

}