package team.msg.hiv2.domain.reservation.application.service

import team.msg.hiv2.global.annotation.service.DomainService

@DomainService
class ReservationService(
    commandReservationService: CommandReservationService,
    queryReservationService: QueryReservationService
) : CommandReservationService by commandReservationService, QueryReservationService by queryReservationService