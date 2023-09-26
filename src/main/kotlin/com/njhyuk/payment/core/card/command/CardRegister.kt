package com.njhyuk.payment.core.card.command

import com.njhyuk.payment.core.card.domain.Card
import com.njhyuk.payment.core.card.domain.CardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CardRegister(
    private val cardRepository: CardRepository
) {
    @Transactional
    fun register(command: Command): Response {
        val maskedCardNo = CardNoMasker.mask(command.cardNo)

        val card = cardRepository.save(
            Card(
                userId = command.userId,
                billingKey = command.billingKey,
                maskedCardNo = maskedCardNo,
                cardName = command.cardName,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )

        return Response(card.id!!)
    }

    data class Command(
        val userId: String,
        val billingKey: String,
        val cardName: String,
        val cardNo: String
    )

    data class Response(
        val cardId: Long
    )
}
