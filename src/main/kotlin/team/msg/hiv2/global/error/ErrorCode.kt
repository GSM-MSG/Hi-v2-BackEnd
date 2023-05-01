package team.msg.hiv2.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // global
    INVALID_TOKEN_TYPE("유효하지 않은 토큰 타입 입니다.", 403),
    INVALID_TOKEN("유효하지 않은 토큰 입니다.", 403),
    EXPIRED_REFRESH_TOKEN("만료된 refreshToken 입니다.", 403),
    EXPIRED_ACCESS_TOKEN("만료된 accessToken 입니다.", 403),

    // user
    USER_NOT_FOUND("유저를 찾을 수 없습니다.", 404),

    // reservation
    RESERVATION_NOT_FOUND("예약을 찾을 수 없습니다.", 404),
    HOME_BASE_TABLE_NOT_FOUND("예약 테이블을 찾을 수 없습니다.", 404),

    // homeBase
    HOME_BASE_NOT_FOUND("홈베이스를 찾을 수 없습니다.", 404),

    INTERVAL_SERVER_ERROR("서버 오류 입니다.", 500);
}