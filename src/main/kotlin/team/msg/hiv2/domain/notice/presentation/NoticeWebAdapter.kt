package team.msg.hiv2.domain.notice.presentation

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.hiv2.domain.notice.application.usecase.CreateNoticeUseCase
import team.msg.hiv2.domain.notice.application.usecase.QueryAllNoticeUseCase
import team.msg.hiv2.domain.notice.application.usecase.QueryNoticeDetailsUseCase
import team.msg.hiv2.domain.notice.presentation.data.request.CreateNoticeRequest
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeResponse
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/notice")
class NoticeWebAdapter(
    private val createNoticeUseCase: CreateNoticeUseCase,
    private val queryAllNoticeUseCase: QueryAllNoticeUseCase,
    private val queryNoticeDetailsUseCase: QueryNoticeDetailsUseCase
) {

    @PostMapping
    fun createNotice(@RequestBody @Valid request: CreateNoticeRequest): ResponseEntity<Void> =
        createNoticeUseCase.execute(request)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping
    fun queryAllNotice(): ResponseEntity<List<NoticeResponse>> =
        queryAllNoticeUseCase.execute()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{notice_id}")
    fun queryNoticeDetails(@PathVariable("notice_id") noticeId: UUID): ResponseEntity<NoticeResponse> =
        queryNoticeDetailsUseCase.execute(noticeId)
            .let { ResponseEntity.ok(it) }
}