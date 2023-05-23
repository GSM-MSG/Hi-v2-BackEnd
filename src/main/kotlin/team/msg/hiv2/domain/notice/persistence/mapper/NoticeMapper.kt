package team.msg.hiv2.domain.notice.persistence.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.notice.domain.Notice
import team.msg.hiv2.domain.notice.persistence.entity.NoticeJpaEntity
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.domain.user.persistence.repository.UserRepository
import team.msg.hiv2.global.mapper.GenericMapper

@Component
class NoticeMapper(
    private val userRepository: UserRepository
) : GenericMapper<Notice, NoticeJpaEntity> {

    override fun toDomain(entity: NoticeJpaEntity?): Notice? =
        entity?.let {
            Notice(
                id = it.id,
                title = it.title,
                content = it.content,
                userId = it.user.id,
                createdAt = it.createdAt
            )
        }

    override fun toEntity(domain: Notice): NoticeJpaEntity{
        val user = userRepository.findByIdOrNull(domain.userId) ?: throw UserNotFoundException()

        return domain.let{
            NoticeJpaEntity(
                id = it.id,
                title = it.title,
                content = it.content,
                user = user,
                createdAt = it.createdAt
            )
        }
    }
}