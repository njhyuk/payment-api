package com.njhyuk.payment.core.subscription.command

import com.njhyuk.payment.core.subscription.exception.NotFoundException
import com.njhyuk.payment.core.subscription.domain.SubscriptionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SubscriptionCanceler(
    private val subscriptionRepository: SubscriptionRepository
) {
    @Transactional
    fun cancel(userId: String): SubscriptionRegisterResponse {
        val subscription = subscriptionRepository.findByUserId(userId)
            ?: throw NotFoundException()

        subscription.cancel()

        return SubscriptionRegisterResponse(subscription.id!!)
    }
}
