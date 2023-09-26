package com.njhyuk.payment.core.subscription.command

import com.njhyuk.payment.core.subscription.domain.SubscriptionRepository
import com.njhyuk.payment.core.subscription.domain.SubscriptionStatus
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SubscriptionDailyPaymentor(
    private val subscriptionRepository: SubscriptionRepository,
    private val subscriptionPaymentor: SubscriptionPaymentor
) {
    fun payment(paymentDate: LocalDate) {
        subscriptionRepository.findByPaymentDateAndStatus(paymentDate, SubscriptionStatus.ACTIVE)
            .forEach {
                subscriptionPaymentor.payment(
                    subscription = it,
                    paymentDate = paymentDate
                )
            }
    }
}
