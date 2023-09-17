package com.njhyuk.payment.core.card.command

data class CardRegisterCommand(
    val userId: String,
    val cardNo: String,
    val expiry: String,
    val password: String,
    val birth: String
)
