package team.msg.hiv2.domain.notice.presentation

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.hiv2.domain.notice.application.usecase.CreateNoticeUseCase
import team.msg.hiv2.domain.notice.application.usecase.DeleteNoticeUseCase
import team.msg.hiv2.domain.notice.application.usecase.QueryAllNoticeUseCase
import team.msg.hiv2.domain.notice.application.usecase.QueryNoticeDetailsUseCase
import team.msg.hiv2.domain.notice.presentation.data.request.CreateNoticeRequest
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeDetailsResponse
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeResponse
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/notice")
class NoticeWebAdapter(
    private val createNoticeUseCase: CreateNoticeUseCase,
    private val queryAllNoticeUseCase: QueryAllNoticeUseCase,
    private val queryNoticeDetailsUseCase: QueryNoticeDetailsUseCase,
    private val deleteNoticeUseCase: DeleteNoticeUseCase
) {

    @PostMapping
    fun createNotice(@RequestBody @Valid request: CreateNoticeRequest): ResponseEntity<Void> =
        createNoticeUseCase.execute(request)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping
    fun queryAllNotice(): ResponseEntity<List<NoticeResponse>> =
        queryAllNoticeUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{id}")
    fun queryNoticeDetails(@PathVariable id: UUID): ResponseEntity<NoticeDetailsResponse> =
        queryNoticeDetailsUseCase.execute(id)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping("/{id}")
    fun deleteNotice(@PathVariable id: UUID): ResponseEntity<Void> =
        deleteNoticeUseCase.execute(id)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }
}