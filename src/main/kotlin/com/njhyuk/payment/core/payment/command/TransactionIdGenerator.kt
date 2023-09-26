package com.njhyuk.payment.core.payment.command

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TransactionIdGenerator {
    fun generate(serviceKey: String, serviceTransactionId: String): String {
        return UUID.randomUUID().toString()
    }
}
