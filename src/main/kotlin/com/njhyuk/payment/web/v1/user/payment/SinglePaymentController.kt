package com.njhyuk.payment.web.v1.user.payment

import com.njhyuk.payment.core.payment.command.SinglePaymentor
import com.njhyuk.payment.core.payment.command.SinglePaymentor.Command
import com.njhyuk.payment.web.WebResponse
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
        @RequestBody request: Request
    ): WebResponse<Response> {
        val payment = singlePaymentor.payment(
            Command(
                cardId = request.cardId,
                userId = userId,
                amount = request.amount,
                reason = request.reason
            )
        )

        return WebResponse.success(Response(payment.paymentId))
    }

    data class Request(
        val cardId: Long,
        val amount: Long,
        val reason: String
    )

    data class Response(
        val paymentId: Long
    )
}
