package com.njhyuk.payment.outbound.event

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class MockEventPublisher(
    private val eventPublisher: ApplicationEventPublisher
) : EventPublisher {
    override fun publish(event: Any) {
        eventPublisher.publishEvent(event)
    }
}
