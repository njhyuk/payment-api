package com.njhyuk.payment.inbound.web.v1.user.card

import com.njhyuk.payment.core.card.command.CardEditor
import com.njhyuk.payment.core.card.command.CardEditor.Command
import com.njhyuk.payment.inbound.web.WebResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class EditCardController(
    private val cardEditor: CardEditor
) {
    @PutMapping("/v1/user/card/{cardId}")
    fun editCard(
        @RequestHeader(name = "user-id") userId: String,
        @PathVariable cardId: Long,
        @RequestBody request: Request
    ): WebResponse<Response> {
        cardEditor.edit(
            Command(
                cardId = cardId,
                cardName = request.cardName
            )
        )

        return WebResponse.success(Response(cardId))
    }

    data class Request(
        val cardName: String
    )

    data class Response(
        val cardId: Long
    )
}
