package com.njhyuk.payment.core.card.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

@Table(indexes = [Index(name = "idx_user_id", columnList = "user_id")])
@Entity
data class Card(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "user_id", nullable = false)
    val userId: String,

    @Column(name = "billing_key", nullable = false, unique = true)
    val billingKey: String,

    @Column(name = "masked_card_no", nullable = false)
    val maskedCardNo: String,

    @Column(name = "card_name", nullable = false)
    var cardName: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime
) {
    fun edit(cardName: String) {
        this.cardName = cardName
        this.updatedAt = LocalDateTime.now()
    }
}
