package com.njhyuk.payment.core.card.command

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BillingKeyGenerator {
    fun generate(): String {
        return UUID.randomUUID().toString()
    }
}
