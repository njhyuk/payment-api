package com.njhyuk.payment.core.subscription.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface SubscriptionPaymentRepository : JpaRepository<SubscriptionPayment, Long> {
    fun findBySubscriptionIdAndPaymentDate(
        subscriptionId: Long,
        paymentDate: LocalDate
    ): SubscriptionPayment?
}
