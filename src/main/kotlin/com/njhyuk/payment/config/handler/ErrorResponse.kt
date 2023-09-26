package com.njhyuk.payment.config.handler

data class ErrorResponse(
    val code: String,
    val message: String
) {
    companion object {
        fun from(errorCode: ErrorCode): ErrorResponse {
            return ErrorResponse(
                code = errorCode.code,
                message = errorCode.message
            )
        }
    }
}
