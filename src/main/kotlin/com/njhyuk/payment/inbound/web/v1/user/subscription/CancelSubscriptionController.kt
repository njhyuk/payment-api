package com.njhyuk.payment.inbound.web.v1.user.subscription

import com.njhyuk.payment.core.subscription.command.SubscriptionCanceler
import com.njhyuk.payment.inbound.web.WebResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class CancelSubscriptionController(
    private val canceler: SubscriptionCanceler
) {
    @PostMapping("/v1/user/subscription/cancel")
    fun cancel(
        @RequestHeader(name = "user-id") userId: String,
        @RequestBody request: Request
    ): WebResponse<Response> {
        val subscription = canceler.cancel(request.subscriptionId)

        return WebResponse.success(Response(subscription.subscriptionId))
    }

    data class Request(
        val subscriptionId: Long
    )

    data class Response(
        val subscriptionId: Long
    )
}
