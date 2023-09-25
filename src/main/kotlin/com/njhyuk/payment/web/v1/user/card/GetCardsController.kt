package com.njhyuk.payment.web.v1.user.card

import com.njhyuk.payment.core.card.query.GetUserCards
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class GetCardsController(
    private val getUserCards: GetUserCards
) {
    @GetMapping("/user/v1/card")
    fun getCards(
        @RequestHeader(name = "user-id") userId: String
    ): GetCardsResponse {
        val cards = getUserCards.getCards(userId)

        return GetCardsResponse.from(cards)
    }
}
