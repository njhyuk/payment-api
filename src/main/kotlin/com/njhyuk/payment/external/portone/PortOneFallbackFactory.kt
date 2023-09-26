package com.njhyuk.payment.external.portone

import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class PortOneFallbackFactory : FallbackFactory<PortOneApiClient> {
    override fun create(cause: Throwable): PortOneApiClient {
        return PortOneFallback(cause)
    }
}
