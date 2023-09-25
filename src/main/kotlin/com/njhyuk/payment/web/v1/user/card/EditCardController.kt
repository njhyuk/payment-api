package com.njhyuk.payment.web.v1.user.card

import com.njhyuk.payment.core.card.command.CardEditor
import com.njhyuk.payment.core.card.command.CardEditorCommand
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
        @RequestBody request: EditCardRequest
    ): EditCardResponse {
        cardEditor.edit(
            CardEditorCommand(
                cardId = cardId,
                cardName = request.cardName
            )
        )

        return EditCardResponse(cardId)
    }
}
