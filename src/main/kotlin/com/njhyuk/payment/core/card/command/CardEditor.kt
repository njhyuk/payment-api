package com.njhyuk.payment.core.card.command

import com.njhyuk.payment.core.card.domain.CardRepository
import com.njhyuk.payment.core.subscription.exception.SubscriptionNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CardEditor(
    private val cardRepository: CardRepository
) {
    @Transactional
    fun edit(command: Command): Response {
        val card = cardRepository.findByIdOrNull(command.cardId)
            ?: throw SubscriptionNotFoundException()

        card.edit(command.cardName)

        return Response(card.id!!)
    }

    data class Command(
        val cardId: Long,
        val cardName: String
    )

    data class Response(
        val cardId: Long
    )
}
