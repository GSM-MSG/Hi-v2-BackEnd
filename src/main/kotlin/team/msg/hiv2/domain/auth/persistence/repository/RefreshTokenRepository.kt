package team.msg.hiv2.domain.auth.persistence.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.scheduling.support.SimpleTriggerContext
import team.msg.hiv2.domain.auth.persistence.entity.RefreshTokenEntity

interface RefreshTokenRepository : CrudRepository<RefreshTokenEntity, String>