package team.msg.hiv2.domain.notice.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.msg.hiv2.domain.notice.persistence.entity.NoticeJpaEntity
import java.util.UUID

interface NoticeRepository : CrudRepository<NoticeJpaEntity, UUID> {

    fun findAllByOrderByCreatedAtDesc(): List<NoticeJpaEntity>
}