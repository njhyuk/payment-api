package com.njhyuk.payment.core.subscription.command

import com.njhyuk.payment.core.card.domain.CardRepository
import com.njhyuk.payment.core.payment.command.SinglePaymentCommand
import com.njhyuk.payment.core.payment.command.SinglePaymentor
import com.njhyuk.payment.core.payment.exception.NotFoundException
import com.njhyuk.payment.core.subscription.domain.SubscriptionRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SubscriptionDailyPaymentor(
    private val cardRepository: CardRepository,
    private val subscriptionRepository: SubscriptionRepository,
    private val singlePaymentor: SinglePaymentor
) {
    fun payment(paymentDate: LocalDate) {
        subscriptionRepository.findByPaymentDate(paymentDate)
            .forEach {
                val card = cardRepository.findByIdAndUserId(
                    id = it.cardId,
                    userId = it.userId
                ) ?: throw NotFoundException()

                singlePaymentor.payment(
                    SinglePaymentCommand(
                        cardId = card.id!!,
                        userId = card.userId,
                        amount = it.amount,
                        reason = "정기결제"
                    )
                )
            }
    }
}
