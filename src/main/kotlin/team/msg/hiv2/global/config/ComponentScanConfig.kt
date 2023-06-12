package team.msg.hiv2.global.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.*
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import team.msg.hiv2.global.annotation.service.DomainService
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase
import team.msg.hiv2.global.annotation.usecase.UseCase

@Configuration
@ComponentScan(
    basePackages = ["team.msg.hiv2"],
    includeFilters = [
        Filter(
            type = FilterType.ANNOTATION,
            classes = [
                DomainService::class,
                UseCase::class,
                ReadOnlyUseCase::class,
            ]
        )
    ]
)
class ComponentScanConfig