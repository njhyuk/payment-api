package com.njhyuk.payment.config.handler

enum class ErrorCode(
    val code: String,
    val message: String
) {
    CARD_NOT_FOUND("CARD_NOT_FOUND", "존재하지 않는 카드입니다."),
    PAYMENT_NOT_FOUND("PAYMENT_NOT_FOUND", "존재하지 않는 결제정보 입니다."),
    SUBSCRIPTION_NOT_FOUND("SUBSCRIPTION_NOT_FOUND", "정기결제 등록 정보가 없습니다.")
}
