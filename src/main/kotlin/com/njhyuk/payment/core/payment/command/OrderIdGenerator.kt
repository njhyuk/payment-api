package com.njhyuk.payment.core.payment.command

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderIdGenerator {
    fun generate(serviceKey: String, serviceOrderId: String): String {
        return UUID.randomUUID().toString()
    }
}
