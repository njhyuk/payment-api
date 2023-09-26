package com.njhyuk.payment.core.subscription.command

import com.njhyuk.payment.core.subscription.domain.SubscriptionRepository
import com.njhyuk.payment.core.subscription.domain.SubscriptionStatus
import com.njhyuk.payment.outbound.event.EventPublisher
import com.njhyuk.payment.outbound.event.SubscriptionPaymentEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class SubscriptionDailyPaymentor(
    private val subscriptionRepository: SubscriptionRepository,
    private val eventPublisher: EventPublisher
) {
    @Transactional(readOnly = true)
    fun payment(paymentDate: LocalDate) {
        subscriptionRepository.streamByPaymentDateAndStatus(paymentDate, SubscriptionStatus.ACTIVE)
            .forEach {
                eventPublisher.publish(
                    SubscriptionPaymentEvent(
                        subscriptionId = it.id!!,
                        paymentDate = paymentDate
                    )
                )
            }
    }
}
