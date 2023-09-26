package com.njhyuk.payment.core.subscription.command

import com.njhyuk.payment.core.card.domain.CardRepository
import com.njhyuk.payment.core.payment.command.PaymentCreator
import com.njhyuk.payment.core.subscription.domain.SubscriptionRepository
import com.njhyuk.payment.core.subscription.domain.SubscriptionStatus
import com.njhyuk.payment.core.subscription.exception.SubscriptionNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDate

const val SUBSCRIPTION_PAYMENT_NAME = "정기결제"

@Service
class SubscriptionDailyPaymentor(
    private val cardRepository: CardRepository,
    private val subscriptionRepository: SubscriptionRepository,
    private val paymentCreator: PaymentCreator
) {
    fun payment(paymentDate: LocalDate) {
        subscriptionRepository.findByPaymentDateAndStatus(paymentDate, SubscriptionStatus.ACTIVE)
            .forEach {
                val card = cardRepository.findByIdAndUserId(it.cardId, it.userId)
                    ?: throw SubscriptionNotFoundException()

                paymentCreator.create(
                    PaymentCreator.Command(
                        cardId = card.id!!,
                        userId = card.userId,
                        amount = it.amount,
                        productName = SUBSCRIPTION_PAYMENT_NAME,
                        serviceKey = it.serviceKey,
                        orderId = it.serviceOrderId
                    )
                )
            }
    }
}
