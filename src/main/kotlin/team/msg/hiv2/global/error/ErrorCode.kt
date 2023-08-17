package team.msg.hiv2.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // global
    INVALID_TOKEN_TYPE("유효하지 않은 토큰 타입 입니다.", 401),
    INVALID_TOKEN("유효하지 않은 토큰 입니다.", 401),
    EXPIRED_REFRESH_TOKEN("만료된 refreshToken 입니다.", 401),
    EXPIRED_ACCESS_TOKEN("만료된 accessToken 입니다.", 401),
    UNEXPECTED_TOKEN("Unexpected Token", 401),
    INVALID_ROLE("검증 되지 않은 권한 입니다.", 401),

    // user
    USER_NOT_FOUND("유저를 찾을 수 없습니다.", 404),
    REFRESH_TOKEN_NOT_FOUND("존재하지 않는 리프레시 토큰", 404),

    // reservation
    RESERVATION_NOT_FOUND("예약을 찾을 수 없습니다.", 404),
    FORBIDDEN_COMMAND_RESERVATION("예약 테이블을 제어할 권한이 없습니다.", 403),
    FORBIDDEN_EXIT_RESERVATION("대표자는 예약을 나갈 수 없습니다.", 403),

    // homeBase
    HOME_BASE_NOT_FOUND("홈베이스를 찾을 수 없습니다.", 404),
    HOME_BASE_FORBIDDEN("예약 가능한 상태가 아닙니다.", 403),

    // notice
    NOTICE_NOT_FOUND("공지사항을 찾을 수 없습니다.", 404),
    FORBIDDEN_COMMAND_NOTICE("공지사항을 제어할 권한이 없습니다.", 403),


    // internal
    FORBIDDEN("FORBIDDEN", 403),
    INTERVAL_SERVER_ERROR("서버 오류 입니다.", 500);
}