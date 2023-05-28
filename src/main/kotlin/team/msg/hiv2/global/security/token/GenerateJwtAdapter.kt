package team.msg.hiv2.global.security.token

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.auth.application.spi.CommandRefreshTokenPort
import team.msg.hiv2.domain.auth.domain.RefreshToken
import team.msg.hiv2.domain.auth.persistence.entity.RefreshTokenEntity
import team.msg.hiv2.domain.auth.persistence.repository.RefreshTokenRepository
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import team.msg.hiv2.domain.user.domain.constant.UserRole
import team.msg.hiv2.global.security.spi.GenerateJwtPort
import java.security.Key
import java.time.LocalDateTime
import java.util.*

@Component
class GenerateJwtAdapter(
    private val jwtProperties: JwtProperties,
    private val commandRefreshTokenPort: CommandRefreshTokenPort
) : GenerateJwtPort{

    override fun generate(userId: UUID, roles: MutableList<UserRole>): TokenResponse {
        val accessToken = generateAccessToken(userId, jwtProperties.accessSecret, roles)
        val refreshToken = generateRefreshToken(userId, jwtProperties.refreshSecret, roles)
        val accessExpiredAt = getAccessTokenExpiredAt()
        val refreshExpiredAt = getRefreshTokenExpiredAt()
        commandRefreshTokenPort.saveRefreshToken(RefreshToken(refreshToken, userId, jwtProperties.refreshExp))
        return TokenResponse(accessToken, refreshToken, accessExpiredAt, refreshExpiredAt)
    }

    private fun generateAccessToken(userId: UUID, secret: Key, roles: MutableList<UserRole>): String =
        Jwts.builder()
            .signWith(secret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim("type", JwtProperties.accessType)
            .claim(JwtProperties.roleType, roles)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp * 1000))
            .compact()

    private fun generateRefreshToken(userId: UUID, secret: Key, roles: MutableList<UserRole>): String =
        Jwts.builder()
            .signWith(secret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim("type", JwtProperties.refreshType)
            .claim(JwtProperties.roleType, roles)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.refreshExp * 1000))
            .compact()

    private fun getAccessTokenExpiredAt(): LocalDateTime =
        LocalDateTime.now().plusSeconds(jwtProperties.accessExp.toLong())

    private fun getRefreshTokenExpiredAt(): LocalDateTime =
        LocalDateTime.now().plusSeconds(jwtProperties.refreshExp.toLong())
}