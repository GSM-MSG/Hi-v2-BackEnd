package team.msg.hiv2.global.security.token

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.auth.application.spi.CommandRefreshTokenPort
import team.msg.hiv2.domain.auth.domain.RefreshToken
import team.msg.hiv2.domain.auth.persistence.entity.RefreshTokenEntity
import team.msg.hiv2.domain.auth.persistence.repository.RefreshTokenRepository
import team.msg.hiv2.domain.auth.presentation.data.response.TokenResponse
import team.msg.hiv2.global.security.spi.GenerateJwtPort
import java.security.Key
import java.time.LocalDateTime
import java.util.*

@Component
class GenerateJwtAdapter(
    private val jwtProperties: JwtProperties,
    private val commandRefreshTokenPort: CommandRefreshTokenPort
) : GenerateJwtPort{

    override fun generate(email: String): TokenResponse {
        val accessToken = generateAccessToken(email, jwtProperties.accessSecret)
        val refreshToken = generateRefreshToken(email, jwtProperties.refreshSecret)
        val accessExpiredAt = getAccessTokenExpiredAt()
        val refreshExpiredAt = getRefreshTokenExpiredAt()
        commandRefreshTokenPort.saveRefreshToken(RefreshToken(refreshToken, email, jwtProperties.refreshExp))
        return TokenResponse(accessToken, refreshToken, accessExpiredAt, refreshExpiredAt)
    }

    private fun generateAccessToken(email: String, secret: Key): String =
        Jwts.builder()
            .signWith(secret, SignatureAlgorithm.HS256)
            .setSubject(email)
            .claim("type", JwtProperties.accessType)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp * 1000))
            .compact()

    private fun generateRefreshToken(email: String, secret: Key): String =
        Jwts.builder()
            .signWith(secret, SignatureAlgorithm.HS256)
            .setSubject(email)
            .claim("type", JwtProperties.refreshType)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.refreshExp * 1000))
            .compact()

    private fun getAccessTokenExpiredAt(): LocalDateTime =
        LocalDateTime.now().plusSeconds(jwtProperties.accessExp.toLong())

    private fun getRefreshTokenExpiredAt(): LocalDateTime =
        LocalDateTime.now().plusSeconds(jwtProperties.refreshExp.toLong())
}