package team.msg.hiv2.domain.auth.application.spi

import team.msg.hiv2.domain.auth.domain.RefreshToken

interface CommandRefreshTokenPort {
    fun saveRefreshToken(refreshToken: RefreshToken)
}