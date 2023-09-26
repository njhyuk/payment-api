package com.njhyuk.payment.core.payment.command

import com.njhyuk.payment.core.payment.domain.Payment
import com.njhyuk.payment.core.payment.domain.PaymentRepository
import com.njhyuk.payment.core.payment.domain.PaymentStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentCreateRecorder(
    private val paymentRepository: PaymentRepository,
    private val paymentHistoryCreator: PaymentHistoryCreator
) {
    @Transactional
    fun record(command: Command): Response {
        val payment = paymentRepository.save(
            Payment(
                userId = command.userId,
                cardId = command.cardId,
                transactionId = command.transactionId,
                partnerPaymentId = command.partnerPaymentId,
                amount = command.amount,
                status = PaymentStatus.PAYMENT,
                productName = command.productName,
                serviceKey = command.serviceKey
            )
        )

        paymentHistoryCreator.create(payment)

        return Response(payment.id!!)
    }

    data class Command(
        val cardId: Long,
        val serviceKey: String,
        val transactionId: String,
        val partnerPaymentId: String,
        val userId: String,
        val amount: Long,
        val productName: String
    )

    data class Response(
        val paymentId: Long
    )
}
