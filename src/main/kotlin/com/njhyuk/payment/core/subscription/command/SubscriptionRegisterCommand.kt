package com.njhyuk.payment.core.subscription.command

import java.time.LocalDate

data class SubscriptionRegisterCommand(
    val cardId: Long,
    val userId: String,
    val amount: Long,
    val paymentDate: LocalDate
)
