package com.njhyuk.payment.inbound.web.v1.batch.subscription

import com.njhyuk.payment.core.subscription.command.SubscriptionDailyPaymentor
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class SubscriptionPaymentController(
    private val subscriptionDailyPaymentor: SubscriptionDailyPaymentor
) {
    @PostMapping("/batch/v1/subscription/payment/{paymentDate}")
    fun execute(
        @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable paymentDate: LocalDate
    ) {
        subscriptionDailyPaymentor.payment(paymentDate)
    }
}
