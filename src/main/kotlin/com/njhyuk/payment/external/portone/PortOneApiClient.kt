package com.njhyuk.payment.external.portone

import com.fasterxml.jackson.annotation.JsonProperty
import mu.KotlinLogging
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

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

    data class GetTokenRequest(
        @JsonProperty("imp_key")
        val impKey: String,
        @JsonProperty("imp_secret")
        val impSecret: String
    )

    data class GetTokenResponse(
        @JsonProperty("access_token")
        val accessToken: String
    )

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
    }
}
