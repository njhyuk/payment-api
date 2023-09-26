package com.njhyuk.payment.core.card.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

@Table(indexes = [Index(name = "idx_user_id", columnList = "userId")])
@Entity
data class Card(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val userId: String,

    @Column(nullable = false, unique = true)
    val billingKey: String,

    @Column(nullable = false)
    val maskedCardNo: String,

    @Column(nullable = false)
    var cardName: String,

    @Column(nullable = false)
    val createdAt: LocalDateTime,

    @Column(nullable = false)
    val updatedAt: LocalDateTime
) {
    fun edit(cardName: String) {
        this.cardName = cardName
    }
}
