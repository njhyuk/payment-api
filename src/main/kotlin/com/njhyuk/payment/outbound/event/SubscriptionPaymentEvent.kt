package com.njhyuk.payment.outbound.event

import java.time.LocalDate

data class SubscriptionPaymentEvent(
    val subscriptionId: Long,
    val paymentDate: LocalDate
)
