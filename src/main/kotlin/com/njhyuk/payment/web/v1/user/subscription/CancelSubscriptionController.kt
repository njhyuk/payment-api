package com.njhyuk.payment.web.v1.user.subscription

import com.njhyuk.payment.core.subscription.command.SubscriptionCanceler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class CancelSubscriptionController(
    private val canceler: SubscriptionCanceler
) {
    @PostMapping("/user/v1/subscription/cancel")
    fun cancel(
        @RequestHeader(name = "user-id") userId: String
    ): Response {
        val subscription = canceler.cancel(userId)

        return Response(subscription.subscriptionId)
    }

    data class Response(
        val subscriptionId: Long
    )
}
