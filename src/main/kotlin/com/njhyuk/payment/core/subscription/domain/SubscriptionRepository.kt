package com.njhyuk.payment.core.subscription.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface SubscriptionRepository : JpaRepository<Subscription, Long> {
    fun findByUserId(userId: String): Subscription?

    fun findByPaymentDate(paymentDate: LocalDate): List<Subscription>
}