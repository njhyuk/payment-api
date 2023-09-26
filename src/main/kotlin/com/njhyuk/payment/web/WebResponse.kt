package com.njhyuk.payment.web

import com.njhyuk.payment.config.handler.ErrorCode

data class WebResponse<E>(
    val success: Boolean,
    val code: String? = null,
    val message: String? = null,
    val data: E?
) {
    companion object {
        fun <E> success(result: E): WebResponse<E> {
            return WebResponse(
                success = true,
                data = result
            )
        }

        fun <E> error(errorCode: ErrorCode): WebResponse<E> {
            return WebResponse(
                success = false,
                code = errorCode.code,
                message = errorCode.message,
                data = null
            )
        }
    }
}
