package com.njhyuk.payment.core.subscription.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate
import java.util.stream.Stream

interface SubscriptionRepository : JpaRepository<Subscription, Long> {
    @Query("select s from Subscription s where s.paymentDate = :paymentDate and s.status = :status")
    fun streamByPaymentDateAndStatus(paymentDate: LocalDate, status: SubscriptionStatus): Stream<Subscription>
}
