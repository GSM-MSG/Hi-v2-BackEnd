package team.msg.hiv2.domain.homebase.application.usecase

import team.msg.hiv2.domain.homebase.application.service.QueryHomeBaseService
import team.msg.hiv2.domain.homebase.application.spi.QueryHomeBasePort
import team.msg.hiv2.domain.homebase.exception.HomeBaseNotFoundException
import team.msg.hiv2.domain.reservation.application.service.QueryReservationService
import team.msg.hiv2.domain.reservation.application.spi.QueryReservationPort
import team.msg.hiv2.domain.reservation.presentation.data.response.ReservationResponse
import team.msg.hiv2.domain.user.application.service.QueryUserService
import team.msg.hiv2.domain.user.application.spi.QueryUserPort
import team.msg.hiv2.domain.user.presentation.data.response.UserResponse
import team.msg.hiv2.global.annotation.usecase.ReadOnlyUseCase

@ReadOnlyUseCase
class QueryReservationByHomeBaseUseCase(
    private val queryReservationService: QueryReservationService,
    private val queryHomeBaseService: QueryHomeBaseService,
    private val queryUserService: QueryUserService
) {

    fun execute(floor: Int, period: Int): List<ReservationResponse>{
        val homeBase = queryHomeBaseService.queryHomeBaseByFloorAndPeriod(floor, period)
        val reservations = queryReservationService.queryAllReservationByHomeBase(homeBase)

        return reservations.map {
            val users = queryUserService.queryAllUserByReservation(it)
            ReservationResponse(
                reservationId = it.id,
                users = users.map { user -> UserResponse(
                        userId = user.id,
                        name = user.name,
                        grade = user.grade,
                        classNum = user.classNum,
                        number = user.number
                    )
                }
            )
        }
    }
}