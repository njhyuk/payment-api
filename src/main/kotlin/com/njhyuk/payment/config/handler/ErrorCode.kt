package com.njhyuk.payment.config.handler

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val code: String,
    val message: String,
    val httpStatus: HttpStatus
) {
    CARD_NOT_FOUND("CARD_NOT_FOUND", "존재하지 않는 카드입니다.", HttpStatus.NOT_FOUND)
}
