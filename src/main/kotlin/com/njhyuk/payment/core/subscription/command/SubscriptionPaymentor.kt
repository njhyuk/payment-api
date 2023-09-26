package com.njhyuk.payment.core.subscription.command

import com.njhyuk.payment.core.card.domain.CardRepository
import com.njhyuk.payment.core.payment.command.PaymentCreator
import com.njhyuk.payment.core.subscription.domain.SubscriptionPayment
import com.njhyuk.payment.core.subscription.domain.SubscriptionPaymentRepository
import com.njhyuk.payment.core.subscription.domain.SubscriptionRepository
import com.njhyuk.payment.core.subscription.exception.SubscriptionDuplicatedException
import com.njhyuk.payment.core.subscription.exception.SubscriptionNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SubscriptionPaymentor(
    private val cardRepository: CardRepository,
    private val subscriptionRepository: SubscriptionRepository,
    private val subscriptionPaymentRepository: SubscriptionPaymentRepository,
    private val paymentCreator: PaymentCreator
) {
    companion object {
        const val SUBSCRIPTION_PAYMENT_NAME = "정기결제"
    }

    fun payment(subscriptionId: Long, paymentDate: LocalDate) {
        val subscription = subscriptionRepository.findByIdOrNull(subscriptionId)
            ?: throw SubscriptionNotFoundException()
        val card = cardRepository.findByIdAndUserId(subscription.cardId, subscription.userId)
            ?: throw SubscriptionNotFoundException()

        val subscriptionPayment = pending(subscription.id!!, paymentDate)

        runCatching {
            paymentCreator.create(
                PaymentCreator.Command(
                    cardId = card.id!!,
                    userId = card.userId,
                    amount = subscription.amount,
                    productName = SUBSCRIPTION_PAYMENT_NAME,
                    serviceKey = subscription.serviceKey,
                    serviceTransactionId = subscription.serviceTransactionId
                )
            )

            success(subscriptionPayment)
        }.getOrElse {
            fail(subscriptionPayment)
        }
    }

    private fun pending(subscriptionId: Long, paymentDate: LocalDate): SubscriptionPayment {
        subscriptionPaymentRepository.findBySubscriptionIdAndPaymentDate(
            subscriptionId = subscriptionId,
            paymentDate = paymentDate
        )?.also {
            throw SubscriptionDuplicatedException()
        }

        return subscriptionPaymentRepository.save(
            SubscriptionPayment(
                subscriptionId = subscriptionId,
                paymentDate = paymentDate
            )
        )
    }

    private fun success(subscriptionPayment: SubscriptionPayment) {
        subscriptionPaymentRepository.save(
            subscriptionPayment.also {
                it.toSuccess()
            }
        )
    }

    private fun fail(subscriptionPayment: SubscriptionPayment) {
        subscriptionPaymentRepository.save(
            subscriptionPayment.also {
                it.toFail()
            }
        )
    }
}
