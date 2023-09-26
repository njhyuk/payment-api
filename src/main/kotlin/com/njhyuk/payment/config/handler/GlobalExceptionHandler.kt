package com.njhyuk.payment.config.handler

import com.njhyuk.payment.core.payment.exception.CardNotFoundException
import com.njhyuk.payment.core.subscription.exception.SubscriptionNotFoundException
import com.njhyuk.payment.exception.BusinessException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private val log = KotlinLogging.logger {}

@RestControllerAdvice
class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CardNotFoundException::class)
    fun cardNotFoundException(e: BusinessException): ErrorResponse {
        log.warn("CardNotFoundException : {}", e.message)
        return ErrorResponse.from(e.errorCode)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SubscriptionNotFoundException::class)
    fun subscriptionNotFoundException(e: BusinessException): ErrorResponse {
        log.warn("SubscriptionNotFoundException : {}", e.message)
        return ErrorResponse.from(e.errorCode)
    }
}
