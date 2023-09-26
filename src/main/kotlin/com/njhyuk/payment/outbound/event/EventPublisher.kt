package com.njhyuk.payment.outbound.event

interface EventPublisher {
    fun publish(event: Any)
}
