package team.msg.hiv2.domain.auth.application.service

import team.msg.hiv2.domain.auth.domain.RefreshToken

interface CommandRefreshTokenService {

    fun save(refreshToken: RefreshToken): String
    fun delete(refreshToken: RefreshToken)
}