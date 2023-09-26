package com.njhyuk.payment.web.v1.user.subscription

import com.njhyuk.payment.core.subscription.command.SubscriptionRegister
import com.njhyuk.payment.core.subscription.command.SubscriptionRegister.Command
import com.njhyuk.payment.web.WebResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterSubscriptionController(
    private val subscriptionRegister: SubscriptionRegister
) {
    @PostMapping("/user/v1/subscription")
    fun register(
        @RequestHeader(name = "user-id") userId: String,
        @RequestBody request: Command
    ): WebResponse<Response> {
        val subscription = subscriptionRegister.register(request)

        return WebResponse.success(Response(subscription.subscriptionId))
    }

    data class Response(
        val subscriptionId: Long
    )
}
