package team.msg.hiv2.domain.auth.application.service

import team.msg.hiv2.domain.auth.domain.RefreshToken

interface QueryRefreshTokenService {

    fun queryByRefreshToken(refreshToken: String): RefreshToken
}