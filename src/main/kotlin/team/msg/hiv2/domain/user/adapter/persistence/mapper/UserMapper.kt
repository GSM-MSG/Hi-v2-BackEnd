package team.msg.hiv2.domain.user.adapter.persistence.mapper

import team.msg.hiv2.domain.user.adapter.persistence.entity.UserJpaEntity
import team.msg.hiv2.domain.user.domain.User
import team.msg.hiv2.global.mapper.GenericMapper

class UserMapper : GenericMapper<User, UserJpaEntity> {

    override fun toDomain(entity: UserJpaEntity?): User? =
        entity?.let {
            User(
                id = it.id,
                email = it.email,
                name = it.name,
                grade = it.grade,
                classNum = it.classNum,
                number = it.number,
                profileImageUrl = it.profileImageUrl,
                roles = it.roles
            )
        }

    override fun toEntity(domain: User): UserJpaEntity =
        domain.let {
            UserJpaEntity(
                id = it.id,
                email = it.email,
                name = it.name,
                grade = it.grade,
                classNum = it.classNum,
                number = it.number,
                profileImageUrl = it.profileImageUrl,
                roles = it.roles
            )
        }
}