package team.msg.hiv2.domain.reservation.application.service

import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class ReservationService(
    queryReservationService: QueryReservationService,
    commandReservationService: CommandReservationService
) : QueryReservationService by queryReservationService,
    CommandReservationService by commandReservationService