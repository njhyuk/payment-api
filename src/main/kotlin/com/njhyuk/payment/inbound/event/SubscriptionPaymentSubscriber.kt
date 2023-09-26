package com.njhyuk.payment.inbound.event

import com.njhyuk.payment.core.subscription.command.SubscriptionPaymentor
import com.njhyuk.payment.outbound.event.SubscriptionPaymentEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class SubscriptionPaymentSubscriber(
    private val subscriptionPaymentor: SubscriptionPaymentor
) {
    @EventListener
    fun handleSubscriptionPaymentEvent(event: SubscriptionPaymentEvent) {
        subscriptionPaymentor.payment(
            subscriptionId = event.subscriptionId,
            paymentDate = event.paymentDate
        )
    }
}
