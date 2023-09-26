package com.njhyuk.payment.inbound.web.v1.user.card

import com.njhyuk.payment.core.card.domain.Card
import com.njhyuk.payment.core.card.query.GetUserCards
import com.njhyuk.payment.inbound.web.WebResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class GetCardsController(
    private val getUserCards: GetUserCards
) {
    @GetMapping("/v1/user/card")
    fun getCards(
        @RequestHeader(name = "user-id") userId: String
    ): WebResponse<Response> {
        val cards = getUserCards.getCards(userId)

        return WebResponse.success(Response.from(cards))
    }

    data class Response(
        val cards: List<CardItem>
    ) {
        companion object {
            fun from(cards: List<Card>): Response {
                return Response(
                    cards.map {
                        CardItem(
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
    }

    data class CardItem(
        val id: Long,
        val cardIssuerId: String,
        var cardName: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    )
}
