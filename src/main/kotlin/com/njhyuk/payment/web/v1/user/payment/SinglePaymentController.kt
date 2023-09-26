package com.njhyuk.payment.web.v1.user.payment

import com.njhyuk.payment.core.payment.command.SinglePaymentor.Command
import com.njhyuk.payment.core.payment.command.SinglePaymentor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class SinglePaymentController(
    private val singlePaymentor: SinglePaymentor
) {
    @PostMapping("/user/v1/payment")
    fun register(
        @RequestHeader(name = "user-id") userId: String,
        @RequestBody request: Command
    ): Response {
        val payment = singlePaymentor.payment(request)

        return Response(payment.paymentId)
    }

    data class Response(
        val paymentId: Long
    )

}
