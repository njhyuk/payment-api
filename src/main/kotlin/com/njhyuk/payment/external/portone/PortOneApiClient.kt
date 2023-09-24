package com.njhyuk.payment.external.portone

import mu.KotlinLogging
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

private val logger = KotlinLogging.logger {}

@FeignClient(
    name = "portone",
    fallbackFactory = PortOneApiClient.ClientFallbackFactory::class
)
interface PortOneApiClient {
    @PostMapping("/users/getToken")
    fun getToken(
        @RequestBody request: GetTokenRequest
    ): PortOneApiResponse<GetTokenResponse>

    @PostMapping("/subscribe/customers/{customer_uid}")
    fun billingKey(
        @RequestHeader(value = "Authorization") authorization: String,
        @PathVariable(value = "customer_uid") customerUid: String,
        @RequestBody request: BillingKeyRequest
    ): PortOneApiResponse<BillingKeyResponse>

    @Component
    class ClientFallbackFactory : FallbackFactory<PortOneApiClient> {
        override fun create(cause: Throwable): PortOneApiClient {
            return ClientFallback(cause)
        }
    }

    class ClientFallback(private val cause: Throwable) : PortOneApiClient {
        override fun getToken(request: GetTokenRequest): PortOneApiResponse<GetTokenResponse> {
            logger.error(cause) { "포트원 인증토큰 발급 실패 :: ${cause.localizedMessage}" }
            throw cause
        }

        override fun billingKey(
            authorization: String,
            customerUid: String,
            request: BillingKeyRequest
        ): PortOneApiResponse<BillingKeyResponse> {
            logger.error(cause) { "빌링키 발급 실패 :: ${cause.localizedMessage}" }
            throw cause
        }
    }
}
