package team.msg.hiv2.domain.auth.application.usecase

import team.msg.hiv2.domain.auth.application.spi.RefreshTokenPort
import team.msg.hiv2.global.annotation.usecase.UseCase

@UseCase
class ReissueTokenUseCase(
    private val refreshTokenPort: RefreshTokenPort
) {
}