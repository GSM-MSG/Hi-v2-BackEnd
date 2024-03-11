package team.msg.hiv2.domain.auth.application.usecase

import team.msg.hiv2.domain.auth.presentation.data.response.GAuthLinkResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase
import team.msg.hiv2.thirdparty.gauth.GAuthProperties

@ReadOnlyUseCase
class QueryGAuthLoginLinkUseCase(
    private val gAuthProperties: GAuthProperties
) {

    companion object {
        const val URL = "https://gauth.co.kr/login?client_id=%s&redirect_uri=%s"
    }

    fun execute() =
        GAuthLinkResponse(
            URL.format(
                gAuthProperties.clientId,
                gAuthProperties.redirectUri
            )
        )
}