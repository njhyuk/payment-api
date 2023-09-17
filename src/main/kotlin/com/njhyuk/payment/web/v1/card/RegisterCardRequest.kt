package com.njhyuk.payment.web.v1.card

data class RegisterCardRequest(
    val cardNo: String,
    val expiry: String,
    val password: String,
    val birth: String
)
