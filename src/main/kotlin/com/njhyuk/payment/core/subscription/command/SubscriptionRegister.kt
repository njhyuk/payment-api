package com.njhyuk.payment.core.subscription.command

import com.njhyuk.payment.core.subscription.domain.Subscription
import com.njhyuk.payment.core.subscription.domain.SubscriptionRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SubscriptionRegister(
    private val subscriptionRepository: SubscriptionRepository
) {
    fun register(command: Command): Response {
        val subscription = subscriptionRepository.save(
            Subscription(
                cardId = command.cardId,
                userId = command.userId,
                amount = command.amount,
                paymentDate = command.paymentDate
            )
        )

        return Response(subscription.id!!)
    }

    data class Command(
        val cardId: Long,
        val userId: String,
        val amount: Long,
        val paymentDate: LocalDate
    )

    data class Response(
        val subscriptionId: Long
    )
}
