package com.njhyuk.payment

import com.fasterxml.jackson.databind.ObjectMapper
import com.njhyuk.payment.core.card.command.BillingRegister
import com.njhyuk.payment.core.card.command.CardRegister
import com.njhyuk.payment.inbound.web.v1.user.payment.PaymentController
import com.njhyuk.payment.restdoc.RestDocsConfiguration
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.UUID

@Ignored("실결제가 되는 테스트이기 때문에 비활성화")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration::class)
@EnableConfigurationProperties(TesterCardConfig::class)
class PaymentIntegrationTest(
    private val objectMapper: ObjectMapper,
    private val mockMvc: MockMvc,
    private val cardRegister: CardRegister,
    private val billingRegister: BillingRegister,
    private val testerCardConfig: TesterCardConfig
) : DescribeSpec({
    describe("단건 결제 API 통합 테스트") {
        val userId = "010-1234-5678"

        val billing = billingRegister.register(
            command = BillingRegister.Command(
                userId = userId,
                cardNo = testerCardConfig.cardNo,
                expiry = YearMonth.parse(testerCardConfig.expiry),
                password = testerCardConfig.password,
                birth = LocalDate.parse(testerCardConfig.birth, DateTimeFormatter.ofPattern("yyMMdd"))
            )
        )

        val card = cardRegister.register(
            CardRegister.Command(
                userId = userId,
                cardNo = testerCardConfig.cardNo,
                billingKey = billing.billingKey,
                cardName = billing.cardName
            )
        )

        context("단건 결제 데이터가 정상이라면") {
            it("200 OK. 정상 결제된다.") {
                val requestBody = PaymentController.Request(
                    serviceKey = "COMMERCE",
                    serviceTransactionId = UUID.randomUUID().toString(),
                    cardId = card.cardId,
                    amount = 100,
                    productName = "반바지"
                )

                mockMvc.perform(
                    post("/v1/user/payment")
                        .header("user-id", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(MockMvcResultMatchers.status().isOk)
            }
        }
    }
})
