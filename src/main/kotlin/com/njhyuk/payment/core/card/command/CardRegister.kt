package com.njhyuk.payment.core.card.command

import com.njhyuk.payment.core.card.domain.Card
import com.njhyuk.payment.core.card.domain.CardRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CardRegister(
    private val cardRepository: CardRepository
) {
    fun register(command: CardRegisterCommand): CardRegisterResponse {
        val card = cardRepository.save(
            Card(
                userId = command.userId,
                billingKey = "",
                cardIssuerId = command.cardNo,
                cardName = "",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                pgId = ""
            )
        )

        return CardRegisterResponse(card.id!!)
    }
}
