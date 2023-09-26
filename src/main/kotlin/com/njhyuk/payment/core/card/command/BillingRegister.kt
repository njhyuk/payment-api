package com.njhyuk.payment.core.card.command

import com.njhyuk.payment.external.portone.PortOneApiClient
import com.njhyuk.payment.external.portone.PortOneConfig
import com.njhyuk.payment.external.portone.dto.BillingKeyRequest
import com.njhyuk.payment.external.portone.dto.GetTokenRequest
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Service
@EnableConfigurationProperties(PortOneConfig::class)
class BillingRegister(
    private val billingKeyGenerator: BillingKeyGenerator,
    private val portOneApiClient: PortOneApiClient,
    private val portOneConfig: PortOneConfig
) {
    companion object {
        val EXPIRY_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM")
        val BIRTH_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyMMdd")
    }

    fun register(command: Command): Response {
        val billingKey = billingKeyGenerator.generate()

        val token = portOneApiClient.getToken(
            GetTokenRequest(
                impKey = portOneConfig.impKey,
                impSecret = portOneConfig.impSecret
            )
        )

        val billing = portOneApiClient.billingKey(
            authorization = token.response.accessToken,
            customerUid = billingKey,
            request = BillingKeyRequest(
                cardNumber = command.cardNo,
                expiry = command.expiry.format(EXPIRY_FORMAT),
                birth = command.birth.format(BIRTH_FORMAT),
                pwd2Digit = command.password
            )
        )

        return Response(
            billingKey = billingKey,
            cardName = billing.response.cardName
        )
    }

    data class Command(
        val userId: String,
        val cardNo: String,
        val expiry: YearMonth,
        val password: String,
        val birth: LocalDate
    )

    data class Response(
        val billingKey: String,
        val cardName: String
    )
}
