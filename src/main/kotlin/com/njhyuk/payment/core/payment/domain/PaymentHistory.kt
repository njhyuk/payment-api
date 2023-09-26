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
data class PaymentHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "payment_id", nullable = false)
    val paymentId: Long,

    @Column(name = "user_id", nullable = false)
    val userId: String,

    @Column(name = "card_id", nullable = false)
    val cardId: Long,

    @Column(name = "transaction_id", nullable = false, unique = true)
    val transactionId: String,

    @Column(name = "partner_payment_id", nullable = false, unique = true)
    val partnerPaymentId: String,

    @Column(name = "service_key", nullable = false)
    val serviceKey: String,

    @Column(name = "product_name", nullable = false)
    val productName: String,

    @Column(name = "amount", nullable = false)
    val amount: Long,

    @Column(name = "cancel_amount", nullable = false)
    var cancelAmount: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: PaymentStatus
)
