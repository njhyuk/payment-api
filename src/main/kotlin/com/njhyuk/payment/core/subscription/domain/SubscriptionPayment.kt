package com.njhyuk.payment.core.subscription.domain

import java.time.LocalDate
import javax.persistence.Entity
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
    val subscriptionId: Long,
    val paymentDate: LocalDate,
    var status: Status = Status.PENDING,
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
