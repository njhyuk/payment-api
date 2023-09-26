package com.njhyuk.payment.core.payment.domain

import javax.persistence.Entity
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
    val userId: String,
    val cardId: Long,
    val orderId: String,
    val partnerPaymentId: String,
    val serviceKey: String,
    val productName: String,
    val amount: Long,
    val cancelAmount: Long = 0,
    val status: PaymentStatus
)
