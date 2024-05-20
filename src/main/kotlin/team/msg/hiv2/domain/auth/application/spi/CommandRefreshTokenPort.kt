package team.msg.hiv2.domain.auth.application.spi

import team.msg.hiv2.domain.auth.domain.RefreshToken

interface CommandRefreshTokenPort {

    fun save(refreshToken: RefreshToken): String
    fun delete(refreshToken: RefreshToken)
    fun deleteById(refreshToken: String)
}