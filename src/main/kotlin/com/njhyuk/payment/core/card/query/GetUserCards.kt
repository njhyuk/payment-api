package com.njhyuk.payment.core.card.query

import com.njhyuk.payment.core.card.domain.Card
import com.njhyuk.payment.core.card.domain.CardRepository
import org.springframework.stereotype.Service

@Service
class GetUserCards(
    private val cardRepository: CardRepository,
) {
    fun getCards(userId: String): List<Card> {
        return cardRepository.findAllByUserId(userId)
    }
}
