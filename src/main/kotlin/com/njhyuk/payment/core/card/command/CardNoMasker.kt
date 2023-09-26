package com.njhyuk.payment.core.card.command

import org.springframework.stereotype.Service

@Service
class CardNoMasker {
    companion object {
        fun mask(cardNo: String): String {
            return cardNo.take(4)
        }
    }
}
