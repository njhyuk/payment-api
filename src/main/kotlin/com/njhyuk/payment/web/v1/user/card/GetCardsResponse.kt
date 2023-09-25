package com.njhyuk.payment.web.v1.user.card

import com.njhyuk.payment.core.card.domain.Card
import java.time.LocalDateTime

data class GetCardsResponse(
    val cards: List<CardResponse>
) {
    companion object {
        fun from(cards: List<Card>): GetCardsResponse {
            return GetCardsResponse(
                cards.map {
                    CardResponse(
                        id = it.id!!,
                        cardIssuerId = it.cardIssuerId,
                        cardName = it.cardName,
                        createdAt = it.createdAt,
                        updatedAt = it.updatedAt
                    )
                }
            )
        }
    }

    data class CardResponse(
        val id: Long,
        val cardIssuerId: String,
        var cardName: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    )
}
