package com.njhyuk.payment.core.payment.command

import com.njhyuk.payment.core.payment.domain.Payment
import com.njhyuk.payment.core.payment.domain.PaymentHistory
import com.njhyuk.payment.core.payment.domain.PaymentHistoryRepository
import org.springframework.stereotype.Service

@Service
class PaymentHistoryCreator(
    private val paymentHistoryRepository: PaymentHistoryRepository
) {
    fun create(payment: Payment) {
        paymentHistoryRepository.save(
            PaymentHistory(
                paymentId = payment.id!!,
                userId = payment.userId,
                cardId = payment.cardId,
                transactionId = payment.transactionId,
                partnerPaymentId = payment.partnerPaymentId,
                serviceKey = payment.serviceKey,
                productName = payment.productName,
                amount = payment.amount,
                cancelAmount = payment.cancelAmount,
                status = payment.status
            )
        )
    }
}
