package com.njhyuk.payment.core.payment.command

import org.springframework.stereotype.Service

@Service
class PaymentCreator(
    private val paymentPartner: PaymentPartner,
    private val paymentRecorder: PaymentRecorder,
    private val orderIdGenerator: OrderIdGenerator
) {
    fun create(command: Command): Response {
        val orderId = orderIdGenerator.generate(command.serviceKey, command.orderId)

        val partner = paymentPartner.payment(
            PaymentPartner.Command(
                cardId = command.cardId,
                orderId = orderId,
                userId = command.userId,
                amount = command.amount,
                productName = command.productName
            )
        )

        val payment = paymentRecorder.create(
            PaymentRecorder.Command(
                cardId = command.cardId,
                orderId = orderId,
                serviceKey = command.serviceKey,
                partnerPaymentId = partner.partnerPaymentId,
                userId = command.userId,
                amount = command.amount,
                productName = command.productName
            )
        )

        return Response(
            paymentId = payment.paymentId,
            orderId = command.orderId
        )
    }

    data class Command(
        val serviceKey: String,
        val orderId: String,
        val cardId: Long,
        val userId: String,
        val amount: Long,
        val productName: String
    )

    data class Response(
        val paymentId: Long,
        val orderId: String
    )
}
