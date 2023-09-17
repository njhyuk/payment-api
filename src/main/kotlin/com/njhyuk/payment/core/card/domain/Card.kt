package com.njhyuk.payment.core.card.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table
@Entity
data class Card(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: String,
    val billingKey: String,
    val maskedCardNo: String,
    val cardName: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
