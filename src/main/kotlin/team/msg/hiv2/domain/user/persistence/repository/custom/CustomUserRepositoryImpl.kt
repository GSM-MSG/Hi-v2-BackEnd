package team.msg.hiv2.domain.user.persistence.repository.custom

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.domain.user.persistence.entity.QUserJpaEntity.userJpaEntity
import team.msg.hiv2.domain.user.persistence.entity.UserJpaEntity

class CustomUserRepositoryImpl (
    private val jpaQueryFactory: JPAQueryFactory
) : CustomUserRepository {

    override fun searchStudent(keyword: String): List<UserJpaEntity> =
        jpaQueryFactory
            .selectFrom(userJpaEntity)
            .where(roleCondition())
            .where(keywordLike(keyword))
            .orderBy(userJpaEntity.schoolNumber.asc())
            .fetch()

    private fun roleCondition(): BooleanExpression =
        userJpaEntity.role.eq(UserRole.ROLE_STUDENT)
            .or(userJpaEntity.role.eq(UserRole.ROLE_ADMIN))

    private fun keywordLike(keyword: String): BooleanExpression? =
        if(keyword == "") null
        else userJpaEntity.name.contains(keyword).or(userJpaEntity.schoolNumber.contains(keyword))

}