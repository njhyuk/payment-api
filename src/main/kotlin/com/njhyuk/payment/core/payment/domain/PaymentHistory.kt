package com.njhyuk.payment.core.payment.domain

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
    val paymentId: Long,
    val userId: String,
    val cardId: Long,
    val transactionId: String,
    val partnerPaymentId: String,
    val serviceKey: String,
    val productName: String,
    val amount: Long,
    val cancelAmount: Long = 0,
    var status: PaymentStatus
)
