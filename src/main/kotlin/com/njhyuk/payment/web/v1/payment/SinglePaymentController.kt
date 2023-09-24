package com.njhyuk.payment.web.v1.payment

import com.njhyuk.payment.core.payment.command.SinglePaymentCommand
import com.njhyuk.payment.core.payment.command.SinglePaymentor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class SinglePaymentController(
    private val singlePaymentor: SinglePaymentor
) {
    @PostMapping("/v1/payment")
    fun register(
        @RequestHeader(name = "user-id") userId: String,
        @RequestBody request: SinglePaymentCommand
    ): SinglePaymentResponse {
        val payment = singlePaymentor.payment(request)

        return SinglePaymentResponse(payment.paymentId)
    }
}
