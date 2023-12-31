package com.njhyuk.payment.core.payment.command

import com.njhyuk.payment.core.card.domain.CardRepository
import com.njhyuk.payment.core.subscription.exception.SubscriptionNotFoundException
import com.njhyuk.payment.external.portone.PortOneApiClient
import com.njhyuk.payment.external.portone.PortOneConfig
import com.njhyuk.payment.external.portone.dto.GetTokenRequest
import com.njhyuk.payment.external.portone.dto.PaymentRequest
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service

@Service
@EnableConfigurationProperties(PortOneConfig::class)
class PaymentCreateExecutor(
    private val cardRepository: CardRepository,
    private val portOneApiClient: PortOneApiClient,
    private val portOneConfig: PortOneConfig
) {
    fun payment(command: Command): Response {
        val card = cardRepository.findByIdAndUserId(command.cardId, command.userId)
            ?: throw SubscriptionNotFoundException()

        val token = portOneApiClient.getToken(
            GetTokenRequest(
                impKey = portOneConfig.impKey,
                impSecret = portOneConfig.impSecret
            )
        )

        val payment = portOneApiClient.payment(
            authorization = token.response.accessToken,
            request = PaymentRequest(
                customerUid = card.billingKey,
                merchantUid = command.transactionId,
                amount = command.amount,
                name = command.productName
            )
        ).response

        return Response(
            partnerPaymentId = payment.impUid,
            amount = payment.amount
        )
    }

    data class Command(
        val cardId: Long,
        val transactionId: String,
        val userId: String,
        val amount: Long,
        val productName: String
    )

    data class Response(
        val partnerPaymentId: String,
        val amount: Long
    )
}
