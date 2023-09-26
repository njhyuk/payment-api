package com.njhyuk.payment.web.v1.user.subscription

import com.njhyuk.payment.core.subscription.command.SubscriptionRegister
import com.njhyuk.payment.core.subscription.command.SubscriptionRegister.Command
import com.njhyuk.payment.web.WebResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class RegisterSubscriptionController(
    private val subscriptionRegister: SubscriptionRegister
) {
    @PostMapping("/v1/user/subscription")
    fun register(
        @RequestHeader(name = "user-id") userId: String,
        @RequestBody request: Request
    ): WebResponse<Response> {
        val subscription = subscriptionRegister.register(
            Command(
                cardId = request.cardId,
                userId = userId,
                amount = request.amount,
                paymentDate = request.paymentDate,
                serviceKey = request.serviceKey,
                serviceOrderId = request.serviceOrderId
            )
        )

        return WebResponse.success(Response(subscription.subscriptionId))
    }

    data class Request(
        val cardId: Long,
        val amount: Long,
        val paymentDate: LocalDate,
        val serviceKey: String,
        val serviceOrderId: String
    )

    data class Response(
        val subscriptionId: Long
    )
}
