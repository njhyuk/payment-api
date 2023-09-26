package com.njhyuk.payment.core.subscription.domain

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class SubscriptionPayment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0L,

    @Column(nullable = false)
    val subscriptionId: Long,

    @Column(nullable = false)
    val paymentDate: LocalDate,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: Status = Status.PENDING
) {
    enum class Status {
        PENDING,
        SUCCESS,
        FAIL
    }

    fun toSuccess() {
        this.status = Status.SUCCESS
    }

    fun toFail() {
        this.status = Status.FAIL
    }
}
