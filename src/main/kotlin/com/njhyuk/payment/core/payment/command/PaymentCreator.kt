package com.njhyuk.payment.core.payment.command

import org.springframework.stereotype.Service

@Service
class PaymentCreator(
    private val paymentExecutor: PaymentCreateExecutor,
    private val paymentRecorder: PaymentCreateRecorder,
    private val transactionIdGenerator: TransactionIdGenerator
) {
    fun create(command: Command): Response {
        val transactionId = transactionIdGenerator.generate(
            serviceKey = command.serviceKey,
            serviceTransactionId = command.serviceTransactionId
        )

        val result = paymentExecutor.payment(
            PaymentCreateExecutor.Command(
                cardId = command.cardId,
                transactionId = transactionId,
                userId = command.userId,
                amount = command.amount,
                productName = command.productName
            )
        )

        val payment = paymentRecorder.record(
            PaymentCreateRecorder.Command(
                cardId = command.cardId,
                transactionId = transactionId,
                serviceKey = command.serviceKey,
                partnerPaymentId = result.partnerPaymentId,
                userId = command.userId,
                amount = command.amount,
                productName = command.productName
            )
        )

        return Response(
            paymentId = payment.paymentId,
            transactionId = command.serviceTransactionId
        )
    }

    data class Command(
        val serviceKey: String,
        val serviceTransactionId: String,
        val cardId: Long,
        val userId: String,
        val amount: Long,
        val productName: String
    )

    data class Response(
        val paymentId: Long,
        val transactionId: String
    )
}
