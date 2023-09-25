package com.njhyuk.payment.core.payment.command

import com.njhyuk.payment.core.card.domain.CardRepository
import com.njhyuk.payment.core.payment.domain.Payment
import com.njhyuk.payment.core.payment.domain.PaymentRepository
import com.njhyuk.payment.core.subscription.exception.NotFoundException
import com.njhyuk.payment.external.portone.PortOneApiClient
import com.njhyuk.payment.external.portone.PortOneConfig
import com.njhyuk.payment.external.portone.dto.GetTokenRequest
import com.njhyuk.payment.external.portone.dto.PaymentRequest
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service

@Service
@EnableConfigurationProperties(PortOneConfig::class)
class SinglePaymentor(
    private val cardRepository: CardRepository,
    private val paymentRepository: PaymentRepository,
    private val portOneApiClient: PortOneApiClient,
    private val portOneConfig: PortOneConfig
) {
    fun payment(command: SinglePaymentCommand): SinglePaymentResponse {
        val card = cardRepository.findByIdAndUserId(command.cardId, command.userId)
            ?: throw NotFoundException()

        val token = portOneApiClient.getToken(
            GetTokenRequest(
                impKey = portOneConfig.impKey,
                impSecret = portOneConfig.impSecret
            )
        )

        portOneApiClient.payment(
            authorization = token.response.accessToken,
            request = PaymentRequest(
                customerUid = card.billingKey,
                merchantUid = portOneConfig.storeId,
                amount = command.amount,
                name = command.reason
            )
        )

        val payment = paymentRepository.save(
            Payment(
                userId = command.userId
            )
        )

        return SinglePaymentResponse(payment.id!!)
    }
}
