package com.njhyuk.payment.core.subscription.command

import com.njhyuk.payment.core.subscription.domain.Subscription
import com.njhyuk.payment.core.subscription.domain.SubscriptionRepository
import org.springframework.stereotype.Service

@Service
class SubscriptionRegister(
    private val subscriptionRepository: SubscriptionRepository
) {
    fun register(command: SubscriptionRegisterCommand): SubscriptionRegisterResponse {
        val subscription = subscriptionRepository.save(
            Subscription(
                cardId = command.cardId,
                userId = command.userId,
                amount = command.amount,
                paymentDate = command.paymentDate
            )
        )

        return SubscriptionRegisterResponse(subscription.id!!)
    }
}
