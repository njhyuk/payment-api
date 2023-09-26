package com.njhyuk.payment.core.payment.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table
@Entity
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val userId: String,

    @Column(nullable = false)
    val cardId: Long,

    @Column(nullable = false, unique = true)
    val transactionId: String,

    @Column(nullable = false, unique = true)
    val partnerPaymentId: String,

    @Column(nullable = false)
    val serviceKey: String,

    @Column(nullable = false)
    val productName: String,

    @Column(nullable = false)
    val amount: Long,

    @Column(nullable = false)
    var cancelAmount: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: PaymentStatus
) {
    fun cancel() {
        this.status = PaymentStatus.CANCEL
        this.cancelAmount = amount
    }
}
