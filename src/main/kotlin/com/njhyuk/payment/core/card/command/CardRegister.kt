package com.njhyuk.payment.core.card.command

import com.njhyuk.payment.core.card.domain.Card
import com.njhyuk.payment.core.card.domain.CardRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CardRegister(
    private val cardRepository: CardRepository
) {
    fun register(command: CardRegisterCommand) {
        cardRepository.save(
            Card(
                id = null,
                userId = command.userId,
                billingKey = "",
                maskedCardNo = command.cardNo,
                cardName = "",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )
    }
}
