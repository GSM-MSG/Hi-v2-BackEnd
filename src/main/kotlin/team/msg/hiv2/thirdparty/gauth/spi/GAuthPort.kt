package team.msg.hiv2.thirdparty.gauth.spi

import gauth.response.GAuthToken
import gauth.response.GAuthUserInfo

interface GAuthPort {

    fun queryGAuthToken(code: String): GAuthToken
    fun queryGAuthUserInfo(accessToken: String): GAuthUserInfo
}