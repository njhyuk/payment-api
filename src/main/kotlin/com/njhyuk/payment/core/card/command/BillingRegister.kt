package com.njhyuk.payment.core.card.command

import com.njhyuk.payment.external.portone.PortOneApiClient
import com.njhyuk.payment.external.portone.PortOneConfig
import com.njhyuk.payment.external.portone.dto.BillingKeyRequest
import com.njhyuk.payment.external.portone.dto.GetTokenRequest
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service

@Service
@EnableConfigurationProperties(PortOneConfig::class)
class BillingRegister(
    private val billingKeyGenerator: BillingKeyGenerator,
    private val portOneApiClient: PortOneApiClient,
    private val portOneConfig: PortOneConfig
) {
    fun register(command: Command): Response {
        val billingKey = billingKeyGenerator.generate()

        val token = portOneApiClient.getToken(
            GetTokenRequest(
                impKey = portOneConfig.impKey,
                impSecret = portOneConfig.impSecret
            )
        )

        val registered = portOneApiClient.billingKey(
            authorization = token.response.accessToken,
            customerUid = billingKey,
            request = BillingKeyRequest(
                cardNumber = command.cardNo,
                expiry = command.expiry,
                birth = command.birth,
                pwd2Digit = command.password
            )
        )

        return Response(
            billingKey = billingKey,
            cardName = registered.response.cardName
        )
    }

    data class Command(
        val userId: String,
        val cardNo: String,
        val expiry: String,
        val password: String,
        val birth: String
    )

    data class Response(
        val billingKey: String,
        val cardName: String
    )
}
