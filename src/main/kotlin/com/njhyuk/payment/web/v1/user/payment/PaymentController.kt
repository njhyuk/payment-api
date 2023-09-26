package com.njhyuk.payment.web.v1.user.payment

import com.njhyuk.payment.core.payment.command.PaymentCreator
import com.njhyuk.payment.core.payment.command.PaymentCreator.Command
import com.njhyuk.payment.web.WebResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController(
    private val paymentCreator: PaymentCreator
) {
    @PostMapping("/v1/user/payment")
    fun register(
        @RequestHeader(name = "user-id") userId: String,
        @RequestBody request: Request
    ): WebResponse<Response> {
        val payment = paymentCreator.create(
            Command(
                serviceKey = request.serviceKey,
                orderId = request.orderId,
                cardId = request.cardId,
                userId = userId,
                amount = request.amount,
                productName = request.productName
            )
        )

        return WebResponse.success(
            Response(
                paymentId = payment.paymentId,
                orderId = payment.orderId
            )
        )
    }

    data class Request(
        val serviceKey: String,
        val orderId: String,
        val cardId: Long,
        val amount: Long,
        val productName: String
    )

    data class Response(
        val paymentId: Long,
        val orderId: String
    )
}
