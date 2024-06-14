package team.msg.hiv2.domain.notice.presentation

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.hiv2.domain.notice.application.facade.NoticeFacade
import team.msg.hiv2.domain.notice.application.usecase.*
import team.msg.hiv2.domain.notice.presentation.data.request.CreateNoticeRequest
import team.msg.hiv2.domain.notice.presentation.data.request.UpdateNoticeRequest
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeDetailsResponse
import team.msg.hiv2.domain.notice.presentation.data.response.NoticeResponse
import team.msg.hiv2.domain.notice.presentation.data.web.CreateNoticeWebRequest
import team.msg.hiv2.domain.notice.presentation.data.web.UpdateNoticeWebRequest
import java.util.*

@RestController
@RequestMapping("/notice")
class NoticeWebAdapter(
    private val noticeFacade: NoticeFacade
) {

    @PostMapping
    fun createNotice(@RequestBody @Valid request: CreateNoticeWebRequest): ResponseEntity<Unit> =
        noticeFacade.createNotice(
            CreateNoticeRequest(
                title = request.title,
                content = request.content
            )
        )
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping
    fun queryAllNotice(): ResponseEntity<List<NoticeResponse>> =
        noticeFacade.queryAllNotice()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{id}")
    fun queryNoticeDetails(@PathVariable id: UUID): ResponseEntity<NoticeDetailsResponse> =
        noticeFacade.queryNoticeDetails(id)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping("/{id}")
    fun deleteNotice(@PathVariable id: UUID): ResponseEntity<Unit> =
        noticeFacade.deleteNotice(id)
            .let { ResponseEntity.noContent().build() }

    @PatchMapping("/{id}")
    fun updateNotice(@PathVariable id: UUID, @RequestBody @Valid request: UpdateNoticeWebRequest): ResponseEntity<Unit> =
        noticeFacade.updateNotice(id,
            UpdateNoticeRequest(
                title = request.title,
                content = request.content
            )
        )
            .let { ResponseEntity.noContent().build() }
}