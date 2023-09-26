package com.njhyuk.payment.core.payment.command

import com.njhyuk.payment.external.portone.PortOneApiClient
import com.njhyuk.payment.external.portone.PortOneConfig
import com.njhyuk.payment.external.portone.dto.GetTokenRequest
import com.njhyuk.payment.external.portone.dto.PaymentCancelRequest
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service

@Service
@EnableConfigurationProperties(PortOneConfig::class)
class PaymentCancelExecutor(
    private val portOneApiClient: PortOneApiClient,
    private val portOneConfig: PortOneConfig
) {
    fun cancel(command: Command): Response {
        val token = portOneApiClient.getToken(
            GetTokenRequest(
                impKey = portOneConfig.impKey,
                impSecret = portOneConfig.impSecret
            )
        )

        val payment = portOneApiClient.cancelPayment(
            authorization = token.response.accessToken,
            request = PaymentCancelRequest(
                impUid = command.partnerPaymentId
            )
        ).response

        return Response(
            partnerPaymentId = payment.impUid,
            amount = payment.amount
        )
    }

    data class Command(
        val partnerPaymentId: String
    )

    data class Response(
        val partnerPaymentId: String,
        val amount: Long
    )
}
