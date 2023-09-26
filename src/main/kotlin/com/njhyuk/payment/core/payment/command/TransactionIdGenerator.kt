package com.njhyuk.payment.core.payment.command

import org.springframework.stereotype.Service

@Service
class TransactionIdGenerator {
    fun generate(serviceKey: String, serviceTransactionId: String): String {
        return serviceKey + "_" + serviceTransactionId
    }
}
