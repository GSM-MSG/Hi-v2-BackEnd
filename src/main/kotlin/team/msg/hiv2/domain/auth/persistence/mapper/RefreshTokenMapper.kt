package team.msg.hiv2.domain.auth.persistence.mapper

import org.springframework.stereotype.Component
import team.msg.hiv2.domain.auth.domain.RefreshToken
import team.msg.hiv2.domain.auth.persistence.entity.RefreshTokenEntity
import team.msg.hiv2.global.mapper.GenericMapper
import kotlin.math.exp

@Component
class RefreshTokenMapper() : GenericMapper<RefreshToken, RefreshTokenEntity> {
    override fun toDomain(entity: RefreshTokenEntity?): RefreshToken? =
        entity?.let {
            RefreshToken(
                refreshToken = it.refreshToken,
                userId = it.userId,
                expiredAt = it.expiredAt
            )
        }

    override fun toEntity(domain: RefreshToken): RefreshTokenEntity =
        domain.let {
            RefreshTokenEntity(
                refreshToken = it.refreshToken,
                userId = it.userId,
                expiredAt = it.expiredAt
            )
        }

}