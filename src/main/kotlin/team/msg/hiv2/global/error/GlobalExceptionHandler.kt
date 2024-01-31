package team.msg.hiv2.global.error

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import team.msg.hiv2.global.error.exception.HiException

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log by lazy { LoggerFactory.getLogger(this.javaClass.simpleName) }

    @ExceptionHandler(HiException::class)
    fun handleHiException(e: HiException): ResponseEntity<ErrorResponse> {
        log.warn("Exception message = ${e.message}")
        return ResponseEntity(
            ErrorResponse(e.errorCode.message,e.errorCode.status),
            HttpStatus.valueOf(e.errorCode.status)
        )
    }
}