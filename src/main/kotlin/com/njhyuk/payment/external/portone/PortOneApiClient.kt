package com.njhyuk.payment.external.portone

import com.njhyuk.payment.external.portone.dto.BillingKeyRequest
import com.njhyuk.payment.external.portone.dto.BillingKeyResponse
import com.njhyuk.payment.external.portone.dto.GetTokenRequest
import com.njhyuk.payment.external.portone.dto.GetTokenResponse
import com.njhyuk.payment.external.portone.dto.PaymentCancelRequest
import com.njhyuk.payment.external.portone.dto.PaymentRequest
import com.njhyuk.payment.external.portone.dto.PaymentResponse
import com.njhyuk.payment.external.portone.dto.PortOneApiResponse
import mu.KotlinLogging
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "portone",
    fallbackFactory = PortOneFallbackFactory::class
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

    @PostMapping("/subscribe/payments/again")
    fun payment(
        @RequestHeader(value = "Authorization") authorization: String,
        @RequestBody request: PaymentRequest
    ): PortOneApiResponse<PaymentResponse>

    @PostMapping("/payments/cancel")
    fun cancelPayment(
        @RequestHeader(value = "Authorization") authorization: String,
        @RequestBody request: PaymentCancelRequest
    ): PortOneApiResponse<PaymentResponse>
}
