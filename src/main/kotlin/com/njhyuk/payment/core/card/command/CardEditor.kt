package com.njhyuk.payment.core.card.command

import com.njhyuk.payment.core.card.domain.CardRepository
import com.njhyuk.payment.core.subscription.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CardEditor(
    private val cardRepository: CardRepository,
) {
    @Transactional
    fun edit(command: CardEditorCommand) {
        val card = cardRepository.findByIdOrNull(command.cardId)
            ?: throw NotFoundException()

        card.edit(command.cardName)
    }
}
