package team.msg.hiv2.thirdparty.gauth.spi

import gauth.GAuthToken
import gauth.GAuthUserInfo

interface GAuthPort {
    fun queryGAuthToken(code: String): GAuthToken
    fun queryGAuthUserInfo(accessToken: String): GAuthUserInfo
}