package com.njhyuk.payment.external.portone.dto

data class PortOneApiResponse<T>(
    val code: Int,
    val message: String?,
    val response: T
)
