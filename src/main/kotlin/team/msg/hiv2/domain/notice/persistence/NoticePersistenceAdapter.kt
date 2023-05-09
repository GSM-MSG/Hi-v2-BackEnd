package team.msg.hiv2.domain.notice.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.notice.application.spi.NoticePort
import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.notice.exception.NoticeNotFoundException
import team.msg.hiv2.domain.notice.persistence.mapper.NoticeMapper
import team.msg.hiv2.domain.notice.persistence.repository.NoticeRepository
import java.util.*

@Component
class NoticePersistenceAdapter(
    private val noticeMapper: NoticeMapper,
    private val noticeRepository: NoticeRepository
) : NoticePort {

    override fun saveNotice(notice: Notice) {
        noticeRepository.save(noticeMapper.toEntity(notice))
    }

    override fun deleteNotice(noticeId: UUID) {
        noticeRepository.deleteById(noticeId)
    }

    override fun queryNotice(): List<Notice?> {
        val notice = noticeRepository.findAll()
        return notice.map { noticeMapper.toDomain(it) }
    }

    override fun queryNoticeById(noticeId: UUID) =
        noticeMapper.toDomain(noticeRepository.findByIdOrNull(noticeId) ?: throw NoticeNotFoundException())
}