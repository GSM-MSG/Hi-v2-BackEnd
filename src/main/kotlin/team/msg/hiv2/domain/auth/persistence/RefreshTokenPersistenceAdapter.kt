package team.msg.hiv2.domain.auth.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.auth.application.spi.RefreshTokenPort
import team.msg.hiv2.domain.auth.domain.RefreshToken
import team.msg.hiv2.domain.auth.persistence.mapper.RefreshTokenMapper
import team.msg.hiv2.domain.auth.persistence.repository.RefreshTokenRepository

@Component
class RefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenMapper: RefreshTokenMapper
) : RefreshTokenPort {

    override fun saveRefreshToken(refreshToken: RefreshToken): String =
        refreshTokenRepository.save(refreshTokenMapper.toEntity(refreshToken)).refreshToken

    override fun queryByRefreshToken(refreshToken: String): RefreshToken? =
        refreshTokenMapper.toDomain(refreshTokenRepository.findByIdOrNull(refreshToken))
}