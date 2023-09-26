package com.njhyuk.payment.core.payment.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table
@Entity
data class PaymentHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val paymentId: Long,

    @Column(nullable = false)
    val userId: String,

    @Column(nullable = false)
    val cardId: Long,

    @Column(nullable = false)
    val transactionId: String,

    @Column(nullable = false)
    val partnerPaymentId: String,

    @Column(nullable = false)
    val serviceKey: String,

    @Column(nullable = false)
    val productName: String,

    @Column(nullable = false)
    val amount: Long,

    @Column(nullable = false)
    val cancelAmount: Long = 0,

    @Column(nullable = false)
    var status: PaymentStatus
)
