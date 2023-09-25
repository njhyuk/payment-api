package com.njhyuk.payment.config.handler

enum class ErrorCode(
    val code: String,
    val message: String
) {
    CARD_NOT_FOUND("CARD_NOT_FOUND", "존재하지 않는 카드입니다.")
}
