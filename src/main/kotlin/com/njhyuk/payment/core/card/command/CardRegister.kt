package com.njhyuk.payment.core.card.command

import com.njhyuk.payment.core.card.domain.Card
import com.njhyuk.payment.core.card.domain.CardRepository
import com.njhyuk.payment.external.portone.PortOneApiClient
import com.njhyuk.payment.external.portone.PortOneConfig
import com.njhyuk.payment.external.portone.dto.BillingKeyRequest
import com.njhyuk.payment.external.portone.dto.GetTokenRequest
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
@EnableConfigurationProperties(PortOneConfig::class)
class CardRegister(
    private val cardRepository: CardRepository,
    private val portOneApiClient: PortOneApiClient,
    private val portOneConfig: PortOneConfig
) {
    fun register(command: CardRegisterCommand): CardRegisterResponse {
        val token = portOneApiClient.getToken(
            GetTokenRequest(
                impKey = portOneConfig.impKey,
                impSecret = portOneConfig.impSecret
            )
        )

        val billingKey = portOneApiClient.billingKey(
            authorization = token.response.accessToken,
            customerUid = UUID.randomUUID().toString(),
            request = BillingKeyRequest(
                cardNumber = command.cardNo,
                expiry = command.expiry,
                birth = command.birth,
                pwd2Digit = command.password
            )
        )

        val card = cardRepository.save(
            Card(
                userId = command.userId,
                billingKey = billingKey.response.customerUid,
                cardIssuerId = command.cardNo,
                cardName = billingKey.response.cardName,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )

        return CardRegisterResponse(card.id!!)
    }
}
