package com.njhyuk.payment.core.subscription.domain

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(indexes = [Index(name = "idx_subscription_daily", columnList = "paymentDate,status")])
data class Subscription(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0L,

    @Column(nullable = false)
    val cardId: Long,

    @Column(nullable = false)
    val userId: String,

    @Column(nullable = false)
    val amount: Long,

    @Column(nullable = false)
    val paymentDate: LocalDate,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: SubscriptionStatus = SubscriptionStatus.ACTIVE,

    @Column(nullable = false)
    val serviceKey: String,

    @Column(nullable = false)
    val serviceTransactionId: String
) {
    fun cancel() {
        this.status = SubscriptionStatus.CANCELLED
    }
}
