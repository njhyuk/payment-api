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

private val logger = KotlinLogging.logger {}

class PortOneFallback(private val cause: Throwable) : PortOneApiClient {
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

    override fun payment(
        authorization: String,
        request: PaymentRequest
    ): PortOneApiResponse<PaymentResponse> {
        logger.error(cause) { "빌링키 결제 실패 :: ${cause.localizedMessage}" }
        throw cause
    }

    override fun cancelPayment(
        authorization: String,
        request: PaymentCancelRequest
    ): PortOneApiResponse<PaymentResponse> {
        logger.error(cause) { "결제 취소 실패 :: ${cause.localizedMessage}" }
        throw cause
    }
}
