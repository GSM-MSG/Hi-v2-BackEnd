package team.msg.hiv2.thirdparty.gauth

import gauth.GAuth
import gauth.response.GAuthToken
import gauth.response.GAuthUserInfo
import org.springframework.stereotype.Component
import team.msg.hiv2.thirdparty.gauth.spi.GAuthPort

@Component
class GAuthAdapter(
    private val gAuth: GAuth,
    private val gAuthProperties: GAuthProperties
) : GAuthPort {

    override fun queryGAuthToken(code: String): GAuthToken =
        gAuth.generateToken(
            code,
            gAuthProperties.clientId,
            gAuthProperties.clientSecret,
            gAuthProperties.redirectUri
        )

    override fun queryGAuthUserInfo(accessToken: String): GAuthUserInfo =
        gAuth.getUserInfo(accessToken)

}