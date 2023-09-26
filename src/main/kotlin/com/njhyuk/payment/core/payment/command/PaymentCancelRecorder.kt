package com.njhyuk.payment.core.payment.command

import com.njhyuk.payment.core.payment.domain.PaymentRepository
import com.njhyuk.payment.core.payment.exception.PaymentNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentCancelRecorder(
    private val paymentRepository: PaymentRepository,
    private val paymentHistoryCreator: PaymentHistoryCreator
) {
    @Transactional
    fun cancel(command: Command): Response {
        val payment = paymentRepository.findByIdWithLock(command.paymentId)
            ?: throw PaymentNotFoundException()

        payment.cancel()
        paymentHistoryCreator.create(payment)

        return Response(payment.id!!)
    }

    data class Command(
        val paymentId: Long,
    )

    data class Response(
        val paymentId: Long
    )
}
