package com.njhyuk.payment.core.subscription.domain

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Subscription(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0L,
    val cardId: Long,
    val userId: String,
    val amount: Long,
    val paymentDate: LocalDate,
    var status: SubscriptionStatus = SubscriptionStatus.ACTIVE,
    val serviceKey: String,
    val serviceTransactionId: String
) {
    fun cancel() {
        this.status = SubscriptionStatus.CANCELLED
    }
}
