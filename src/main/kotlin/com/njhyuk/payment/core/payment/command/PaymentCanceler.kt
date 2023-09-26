package com.njhyuk.payment.core.payment.command

import org.springframework.stereotype.Service

@Service
class PaymentCanceler(
    private val paymentCancelExecutor: PaymentCancelExecutor,
    private val paymentCancelRecorder: PaymentCancelRecorder
) {
    fun cancel(paymentId: Long, partnerPaymentId: String) {
        paymentCancelExecutor.cancel(
            PaymentCancelExecutor.Command(
                partnerPaymentId = partnerPaymentId
            )
        )

        paymentCancelRecorder.record(
            PaymentCancelRecorder.Command(
                paymentId = paymentId
            )
        )
    }
}
