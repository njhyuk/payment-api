package com.njhyuk.payment.core.subscription.command

import com.njhyuk.payment.core.subscription.domain.SubscriptionRepository
import com.njhyuk.payment.core.subscription.exception.SubscriptionNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SubscriptionCanceler(
    private val subscriptionRepository: SubscriptionRepository
) {
    @Transactional
    fun cancel(subscriptionId: Long): Response {
        val subscription = subscriptionRepository.findByIdOrNull(subscriptionId)
            ?: throw SubscriptionNotFoundException()

        subscription.cancel()

        return Response(subscription.id!!)
    }

    data class Response(
        val subscriptionId: Long
    )
}
