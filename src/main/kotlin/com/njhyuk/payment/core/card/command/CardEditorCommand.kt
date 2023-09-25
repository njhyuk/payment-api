package com.njhyuk.payment.core.card.command

data class CardEditorCommand(
    val cardId: Long,
    val cardName: String,
)
