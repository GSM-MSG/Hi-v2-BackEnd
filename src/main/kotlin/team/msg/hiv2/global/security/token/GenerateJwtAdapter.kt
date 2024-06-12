package team.msg.hiv2.global.security.token

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import team.msg.hiv2.domain.auth.application.spi.CommandRefreshTokenPort
import team.msg.hiv2.domain.auth.domain.RefreshToken
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

    override fun generate(userId: UUID, role: UserRole): TokenResponse {
        val accessToken = generateAccessToken(userId, jwtProperties.accessSecret, role)
        val refreshToken = generateRefreshToken(userId, jwtProperties.refreshSecret, role)
        val accessExpiredAt = getAccessTokenExpiredAt()
        val refreshExpiredAt = getRefreshTokenExpiredAt()
        commandRefreshTokenPort.save(RefreshToken(refreshToken, userId, jwtProperties.refreshExp))
        return TokenResponse(accessToken, refreshToken, accessExpiredAt, refreshExpiredAt)
    }

    private fun generateAccessToken(userId: UUID,secret: Key, role: UserRole): String =
        Jwts.builder()
            .signWith(secret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim(TYPE, JwtProperties.accessType)
            .claim(JwtProperties.roleType, role)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp * 1000))
            .compact()

    private fun generateRefreshToken(userId: UUID,secret: Key, role: UserRole): String =
        Jwts.builder()
            .signWith(secret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim(TYPE, JwtProperties.refreshType)
            .claim(JwtProperties.roleType, role)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.refreshExp * 1000))
            .compact()

    private fun getAccessTokenExpiredAt(): LocalDateTime =
        LocalDateTime.now().plusSeconds(jwtProperties.accessExp.toLong())

    private fun getRefreshTokenExpiredAt(): LocalDateTime =
        LocalDateTime.now().plusSeconds(jwtProperties.refreshExp.toLong())

    companion object {
        private const val TYPE = "type"
    }
}