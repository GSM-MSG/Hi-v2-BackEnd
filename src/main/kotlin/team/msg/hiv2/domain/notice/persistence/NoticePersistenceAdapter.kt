package team.msg.hiv2.domain.notice.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.notice.application.spi.NoticePort
import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.notice.persistence.mapper.NoticeMapper
import team.msg.hiv2.domain.notice.persistence.repository.NoticeRepository
import java.util.*

@Component
class NoticePersistenceAdapter(
    private val noticeMapper: NoticeMapper,
    private val noticeRepository: NoticeRepository
) : NoticePort {

    override fun save(notice: Notice): Notice =
        noticeMapper.toDomain(noticeRepository.save(noticeMapper.toEntity(notice)))!!

    override fun delete(notice: Notice) {
        noticeRepository.delete(noticeMapper.toEntity(notice))
    }

    override fun queryAllNoticeByOrderByCreatedAtDesc(): List<Notice>  =
        noticeRepository.findAllByOrderByCreatedAtDesc().map { noticeMapper.toDomain(it)!! }

    override fun queryNoticeById(id: UUID) =
        noticeMapper.toDomain(noticeRepository.findByIdOrNull(id))
}