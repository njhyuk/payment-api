package com.njhyuk.payment.web.v1.user.card

import com.njhyuk.payment.core.card.command.CardEditor
import com.njhyuk.payment.core.card.command.CardEditor.Command
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class EditCardController(
    private val cardEditor: CardEditor
) {
    @PutMapping("/user/v1/card/{cardId}")
    fun editCard(
        @RequestHeader(name = "user-id") userId: String,
        @PathVariable cardId: Long,
        @RequestBody request: Request
    ): Response {
        cardEditor.edit(
            Command(
                cardId = cardId,
                cardName = request.cardName
            )
        )

        return Response(cardId)
    }

    data class Request(
        val cardName: String,
    )

    data class Response(
        val cardId: Long
    )
}
