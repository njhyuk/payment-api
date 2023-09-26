package com.njhyuk.payment.core.subscription.domain

import com.njhyuk.payment.core.card.domain.Card
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(indexes = [Index(name = "idx_subscription_daily", columnList = "payment_date,status")])
data class Subscription(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0L,

    @Column(name = "card_id", nullable = false)
    val cardId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", insertable = false, updatable = false)
    val card: Card? = null,

    @Column(name = "user_id", nullable = false)
    val userId: String,

    @Column(name = "amount", nullable = false)
    val amount: Long,

    @Column(name = "payment_date", nullable = false)
    val paymentDate: LocalDate,

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: SubscriptionStatus = SubscriptionStatus.ACTIVE,

    @Column(name = "service_key", nullable = false)
    val serviceKey: String,

    @Column(name = "service_transaction_id", nullable = false)
    val serviceTransactionId: String
) {
    fun cancel() {
        this.status = SubscriptionStatus.CANCELLED
    }
}
