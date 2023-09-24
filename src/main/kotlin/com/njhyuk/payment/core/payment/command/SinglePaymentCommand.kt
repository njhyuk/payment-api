package com.njhyuk.payment.core.payment.command

data class SinglePaymentCommand(
    val cardId: Long,
    val userId: String,
    val amount: Long,
    val reason: String
)
