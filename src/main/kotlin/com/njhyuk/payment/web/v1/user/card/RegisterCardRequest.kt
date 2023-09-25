package com.njhyuk.payment.web.v1.user.card

data class RegisterCardRequest(
    val cardNo: String,
    val expiry: String,
    val password: String,
    val birth: String
)
